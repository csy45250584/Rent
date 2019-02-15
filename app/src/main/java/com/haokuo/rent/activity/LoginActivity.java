package com.haokuo.rent.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haokuo.rent.R;
import com.haokuo.rent.base.BaseActivity;
import com.haokuo.rent.bean.LoginResultBean;
import com.haokuo.rent.consts.SpConsts;
import com.haokuo.rent.network.EntityCallback;
import com.haokuo.rent.network.HttpHelper;
import com.haokuo.rent.network.NetworkCallback;
import com.haokuo.rent.network.params.LoginByPasswordParams;
import com.haokuo.rent.network.params.LoginByTelParams;
import com.haokuo.rent.network.params.base.TelPhoneParams;
import com.haokuo.rent.util.MySpUtil;
import com.haokuo.rent.util.utilscode.RegexUtils;
import com.haokuo.rent.util.utilscode.SPUtils;
import com.haokuo.rent.util.utilscode.ScreenUtils;
import com.haokuo.rent.util.utilscode.ToastUtils;
import com.haokuo.rent.view.CountDownTextView;
import com.rey.material.widget.Button;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zjf on 2018/9/7.
 */
public class LoginActivity extends BaseActivity {
    private static final int CODE_NUM = 4;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.et_tel)
    TextInputEditText mEtTel;
    @BindView(R.id.et_code)
    TextInputEditText mEtCode;
    @BindView(R.id.til_code)
    TextInputLayout mTilCode;
    @BindView(R.id.tv_get_code)
    CountDownTextView mTvGetCode;
    @BindView(R.id.tv_login_by_password)
    TextView mTvLoginByPassword;
    @BindView(R.id.ll_tel_login_container)
    LinearLayout mLlTelLoginContainer;
    @BindView(R.id.et_account)
    TextInputEditText mEtAccount;
    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;
    @BindView(R.id.tv_login_by_tel)
    TextView mTvLoginByTel;
    @BindView(R.id.ll_password_login_container)
    LinearLayout mLlPasswordLoginContainer;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_forget_password)
    TextView mTvForgetPassword;

    private boolean mIsTelLogin;

    private NetworkCallback mLoginCallback;

    @Override
    protected int initContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        ScreenUtils.printScreenInfo();
        mIsTelLogin = true;
        applyUiByState();
        mEtCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(CODE_NUM)});
        mLoginCallback = new EntityCallback<LoginResultBean>() {
            @Override
            public void onFailure(Call call, String message) {
                loadFailed("登录失败，" + message);
            }

            @Override
            public void onSuccess(Call call, LoginResultBean result) {
                SPUtils spUtils = SPUtils.getInstance(SpConsts.FILE_PERSONAL_INFORMATION);
                spUtils.put(SpConsts.KEY_USER_ID, result.getUserId());
                spUtils.put(SpConsts.KEY_TOKEN, result.getToken());
                String tel = mIsTelLogin ? mEtTel.getEditableText().toString().trim() : mEtAccount.getEditableText().toString().trim();
                MySpUtil.getInstance().saveTel(tel);
                loadSuccess("登录成功", new LoadingDialog.OnFinishListener() {
                    @Override
                    public void onFinish() {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {// 注册成功
            showLoading("登录中...");
            String tel = data.getStringExtra(RegisterActivity.EXTRA_TEL);
            String password = data.getStringExtra(RegisterActivity.EXTRA_PASSWORD);
            LoginByPasswordParams params = new LoginByPasswordParams(tel, password);
            HttpHelper.getInstance().loginByPassword(params, mLoginCallback);
        }
    }

    @OnClick({R.id.btn_register, R.id.tv_get_code, R.id.btn_login, R.id.tv_login_by_password, R.id.tv_login_by_tel, R.id.tv_forget_password})
    public void onViewClicked(View view) {
                switch (view.getId()) {
                    case R.id.btn_register:
                        startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), 0);
                        break;
                    case R.id.tv_get_code: {
                        if(mTvGetCode.isCanGetCode()) {
                            String tel = mEtTel.getEditableText().toString().trim();
                            if (!RegexUtils.isMobileSimple(tel)) {
                                ToastUtils.showShort("请输入正确的手机号码");
                                return;
                            }
                            mTvGetCode.sendingCode();
                            TelPhoneParams params = new TelPhoneParams(tel);
                            HttpHelper.getInstance().getLoginCode(params, new NetworkCallback() {
                                @Override
                                public void onSuccess(Call call, String json) {
                                    mTvGetCode.startCountTime();
                                }

                                @Override
                                public void onFailure(Call call, String message) {
                                    ToastUtils.showShort("获取验证码失败，" + message);
                                    mTvGetCode.resetGetCode();
                                }
                            });
                        }
                    }
                    break;
                    case R.id.btn_login: {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        if (mIsTelLogin) {
                            String tel = mEtTel.getEditableText().toString().trim();
                            String code = mEtCode.getEditableText().toString().trim();
                            if (!RegexUtils.isMobileSimple(tel)) {
                                ToastUtils.showShort("请输入正确的手机号码");
                                return;
                            }
                            if (code.length() != CODE_NUM) {
                                ToastUtils.showShort("验证码格式错误");
                                return;
                            }
                            LoginByTelParams params = new LoginByTelParams(tel, code);
                            showLoading("登录中...");
                            HttpHelper.getInstance().loginByTel(params, mLoginCallback);
                        } else {
                            String account = mEtAccount.getEditableText().toString().trim();
                            String password = mEtPassword.getEditableText().toString().trim();
                            if (!RegexUtils.isSimplePassword(password)) {
                                ToastUtils.showShort("密码格式错误");
                                return;
                            }
                            showLoading("登录中...");
                            LoginByPasswordParams params = new LoginByPasswordParams(account, password);
                            HttpHelper.getInstance().loginByPassword(params, mLoginCallback);
                        }
                    }
                    break;
                    case R.id.tv_login_by_password:
                        mIsTelLogin = false;
                        applyUiByState();
                        break;
                    case R.id.tv_login_by_tel:
                        mIsTelLogin = true;
                        applyUiByState();
                        break;
                    case R.id.tv_forget_password:
                        break;
                }
    }

    private void applyUiByState() {
        mLlTelLoginContainer.setVisibility(mIsTelLogin ? View.VISIBLE : View.GONE);
        mTvLoginByPassword.setVisibility(mIsTelLogin ? View.VISIBLE : View.GONE);
        mLlPasswordLoginContainer.setVisibility(mIsTelLogin ? View.GONE : View.VISIBLE);
        mTvLoginByTel.setVisibility(mIsTelLogin ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        mTvGetCode.stopCountTime();
        super.onDestroy();
    }


}
