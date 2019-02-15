package com.haokuo.rent.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haokuo.rent.R;
import com.haokuo.rent.base.BaseActivity;
import com.haokuo.rent.network.HttpHelper;
import com.haokuo.rent.network.NetworkCallback;
import com.haokuo.rent.network.params.RegisterByTelParams;
import com.haokuo.rent.network.params.base.TelPhoneParams;
import com.haokuo.rent.util.utilscode.RegexUtils;
import com.haokuo.rent.util.utilscode.ToastUtils;
import com.haokuo.rent.view.CountDownTextView;
import com.rey.material.widget.Button;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zjf on 2018/10/31.
 */
public class RegisterActivity extends BaseActivity {
    public static final String EXTRA_TEL = "com.haokuo.rent.extra.EXTRA_TEL";
    public static final String EXTRA_PASSWORD = "com.haokuo.rent.extra.EXTRA_PASSWORD";
    private static final int CODE_NUM = 4;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.et_tel)
    TextInputEditText mEtTel;
    @BindView(R.id.tv_register_by_email)
    TextView mTvRegisterByEmail;
    @BindView(R.id.btn_get_code)
    Button mBtnGetCode;
    @BindView(R.id.ll_step1_container)
    LinearLayout mLlStep1Container;
    @BindView(R.id.et_code)
    TextInputEditText mEtCode;
    @BindView(R.id.tv_get_code)
    CountDownTextView mTvGetCode;
    @BindView(R.id.et_tel_password)
    TextInputEditText mEtTelPassword;
    @BindView(R.id.btn_register_by_tel)
    Button mBtnRegisterByTel;
    @BindView(R.id.ll_step2_container)
    LinearLayout mLlStep2Container;
    @BindView(R.id.ll_tel_register_container)
    LinearLayout mLlTelRegisterContainer;
    @BindView(R.id.et_email)
    TextInputEditText mEtEmail;
    @BindView(R.id.et_email_password)
    TextInputEditText mEtEmailPassword;
    @BindView(R.id.tv_register_by_tel)
    TextView mTvRegisterByTel;
    @BindView(R.id.btn_register_by_email)
    Button mBtnRegisterByEmail;
    @BindView(R.id.ll_email_register_container)
    LinearLayout mLlEmailRegisterContainer;

    @Override
    protected int initContentLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        //设置初始界面
        mLlTelRegisterContainer.setVisibility(View.VISIBLE);
        mLlStep1Container.setVisibility(View.VISIBLE);
        mLlStep2Container.setVisibility(View.GONE);
        mLlEmailRegisterContainer.setVisibility(View.GONE);
    }

    @OnClick({R.id.btn_login, R.id.tv_register_by_email, R.id.btn_get_code, R.id.tv_get_code, R.id.btn_register_by_tel, R.id.tv_register_by_tel, R.id.btn_register_by_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                finish();
                break;
            case R.id.tv_register_by_email:
                mLlTelRegisterContainer.setVisibility(View.GONE);
                mLlEmailRegisterContainer.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_get_code: {
                String tel = mEtTel.getEditableText().toString().trim();
                if (!RegexUtils.isTel(tel)) {
                    ToastUtils.showShort("请填写正确的电话号码");
                    return;
                }
                TelPhoneParams params = new TelPhoneParams(tel);
                showLoading("正在发送验证码...");
                HttpHelper.getInstance().getRegisterCode(params, new NetworkCallback() {
                    @Override
                    public void onSuccess(Call call, String json) {
                        loadSuccess("发送成功，请注意查收", new LoadingDialog.OnFinishListener() {
                            @Override
                            public void onFinish() {
                                mLlStep1Container.setVisibility(View.GONE);
                                mLlStep2Container.setVisibility(View.VISIBLE);
                                mTvGetCode.startCountTime();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call call, String message) {
                        loadFailed("获取验证码失败，" + message);
                    }
                });
            }
            break;
            case R.id.tv_get_code: {
                if (mTvGetCode.isCanGetCode()) {
                    String tel = mEtTel.getEditableText().toString().trim();
                    if (!RegexUtils.isTel(tel)) {
                        ToastUtils.showShort("请填写正确的电话号码");
                        return;
                    }
                    TelPhoneParams params = new TelPhoneParams(tel);
                    mTvGetCode.sendingCode();
                    HttpHelper.getInstance().getRegisterCode(params, new NetworkCallback() {
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
            case R.id.btn_register_by_tel: {
                final String tel = mEtTel.getEditableText().toString().trim();
                if (!RegexUtils.isTel(tel)) {
                    ToastUtils.showShort("请填写正确的电话号码");
                    return;
                }
                String code = mEtCode.getEditableText().toString().trim();
                if (code.length() != CODE_NUM) {
                    ToastUtils.showShort("验证码格式错误");
                    return;
                }
                final String telPassword = mEtTelPassword.getEditableText().toString().trim();
                if (!RegexUtils.isSimplePassword(telPassword)) {
                    ToastUtils.showShort("密码格式错误");
                    return;
                }
                RegisterByTelParams params = new RegisterByTelParams(tel, code);
                showLoading("注册中...");
                HttpHelper.getInstance().registerByTel(params, new NetworkCallback() {
                    @Override
                    public void onSuccess(Call call, String json) {
                        loadSuccess("注册成功");
                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_TEL,tel);
                        intent.putExtra(EXTRA_PASSWORD,telPassword);
                        setResult(RESULT_OK,intent);
                    }

                    @Override
                    public void onFailure(Call call, String message) {
                        loadFailed("注册失败，" + message);
                    }
                });
            }
            break;
            case R.id.tv_register_by_tel:
                mLlTelRegisterContainer.setVisibility(View.VISIBLE);
                mLlEmailRegisterContainer.setVisibility(View.GONE);
                break;
            case R.id.btn_register_by_email: {
                String email = mEtEmail.getEditableText().toString().trim();
                if (!RegexUtils.isEmail(email)) {
                    ToastUtils.showShort("请填写正确的电子邮箱");
                    return;
                }
                //TODO 邮箱注册
            }
            break;
        }
    }
}
