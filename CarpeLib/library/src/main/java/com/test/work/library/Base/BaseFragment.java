package com.test.work.library.Base;


import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.test.work.library.R;


/**
 * Base Fragment
 * 1.共用 ComanagementListener
 * 2.動畫載入完成後動作
 */
public class BaseFragment extends Fragment {


    protected SharedPreferences.Editor editor;

    /**
     * 進場動畫
     */
    protected int enterAnimation = R.anim.slide_left_in;

    /**
     * 離場動畫
     */
    protected int outAnimation = R.anim.slide_right_out;

    public ComanagementListener comanagementListener;

    public boolean isAnimation = true;

    public AppCompatActivity mPage;

    public FragmentManager fragmentManager;

    public BaseFragment() {
        // Required empty public constructor
    }

    /**
     * 動畫 載入完成後 要做的動作
     */
    protected void afterEnterAnimation() {
    }

    /**
     * back按鍵動作
     */
    public void doBack() {
        comanagementListener.doBack();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // init
            comanagementListener = (ComanagementListener) activity;
            mPage = (AppCompatActivity) activity;
            fragmentManager = mPage.getSupportFragmentManager();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ComanagementListener");
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = null;
        if (isAnimation) {
            if (enter) {
                animation = AnimationUtils.loadAnimation(getActivity(), enterAnimation);
            } else {
                animation = AnimationUtils.loadAnimation(getActivity(), outAnimation);
            }
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    afterEnterAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        return animation;
    }

//    /**
//     * Called when a fragment loads an animation.
//     *
//     * @param transit
//     * @param enter
//     * @param nextAnim
//     */
//    @Override
//    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
//        Animator animator = null;
//        if (enter) {
//            animator = AnimatorInflater.loadAnimator(getActivity(), enterAnimation);
//        } else {
//            animator = AnimatorInflater.loadAnimator(getActivity(), outAnimation);
//        }
//
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                afterEnterAnimation();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        return animator;
//    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setAnimation(int enterAnimation, int outAnimation) {
        this.enterAnimation = enterAnimation;
        this.outAnimation = outAnimation;
    }

    public void removeSelf() {
        fragmentManager.beginTransaction().remove(this).commit();
    }

}
