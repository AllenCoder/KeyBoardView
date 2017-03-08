package com.kamkeyboard.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kamkeyboard.R;
import com.kamkeyboard.entity.KeyBoardItem;
import com.kamkeyboard.util.StringUtils;

import java.util.List;

/**
 * Created by Kam on 17/1/18.
 */

public class KeyBoardAdapter extends BaseMultiItemQuickAdapter<KeyBoardItem, BaseViewHolder> {
    public boolean mIsUpper = false;// 是否大写
    public boolean mIsNum = false;// 是否数字

    public void setUpper(boolean upper) {
        mIsUpper = upper;
    }

    public void setIsNum(boolean mIsNum) {
        this.mIsNum = mIsNum;
    }

    public KeyBoardAdapter(Context context, List data) {
        super(data);
        addItemType(KeyBoardItem.NUMBER, R.layout.item_keyboard_num);
        addItemType(KeyBoardItem.ALPHABET_NORMAL, R.layout.item_keyboard_alphabet_normal);
        addItemType(KeyBoardItem.ALPHABET_A, R.layout.item_keyboard_alphabet_a);
        addItemType(KeyBoardItem.IMG_CAPITAL, R.layout.item_keyboard_img_alphabet);
        addItemType(KeyBoardItem.NUM_IMG_BACK, R.layout.item_keyboard_img_num);
        addItemType(KeyBoardItem.ALPHABET_IMG_BACK, R.layout.item_keyboard_img_alphabet);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, KeyBoardItem item) {
        switch (baseViewHolder.getItemViewType()) {
            case KeyBoardItem.NUM_IMG_BACK:
                baseViewHolder.setImageResource(R.id.img_kb_item_img, R.drawable.iv_common_kb_delete_big);
                break;
            case KeyBoardItem.ALPHABET_IMG_BACK:
                baseViewHolder.setImageResource(R.id.img_kb_item_img_alphabet, R.drawable.iv_common_kb_delete_small);
                break;
            case KeyBoardItem.IMG_CAPITAL:
                if (!mIsUpper) {// 小写
                    baseViewHolder.setImageResource(R.id.img_kb_item_img_alphabet, R.drawable.iv_commom_kb_lowercase);
                    baseViewHolder.setBackgroundRes(R.id.img_kb_item_img_alphabet, R.drawable.selector_keyboard_key_alphabet);
                } else {
                    baseViewHolder.setImageResource(R.id.img_kb_item_img_alphabet, R.drawable.iv_commom_kb_capital);
                    baseViewHolder.setBackgroundRes(R.id.img_kb_item_img_alphabet, R.drawable.shape_keyboard_alphabet_selected);
                }
                break;
            case KeyBoardItem.NUMBER:
                baseViewHolder.setText(R.id.tv_kb, item.getContent());
                break;
            default:
                if (!mIsUpper) {// 小写
                    if (!StringUtils.isEmpty(item.getContent()) && isWord(item.getContent())) {
                        baseViewHolder.setText(R.id.tv_kb_item_abc, item.getContent().toLowerCase());
                    }
                } else {// 大写
                    if (!StringUtils.isEmpty(item.getContent()) && isWord(item.getContent())) {
                        baseViewHolder.setText(R.id.tv_kb_item_abc, item.getContent().toUpperCase());
                    }
                }
                break;
        }

    }

    private boolean isWord(String str) {
        String wordStr = "abcdefghijklmnopqrstuvwxyz";
        return wordStr.contains(str.toLowerCase());
    }

}
