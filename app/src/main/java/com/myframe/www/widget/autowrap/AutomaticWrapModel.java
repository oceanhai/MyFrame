package com.myframe.www.widget.autowrap;

/**
 * 问题数据结构
 */
public class AutomaticWrapModel {
    private String caseText;
    private boolean isDefault = false;
    private boolean isExclusive = false;

    public String getCaseText() {
        return caseText;
    }

    public void setCaseText(String caseText) {
        this.caseText = caseText;
    }

    /**
     * 是否选中
     * @return
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * 设置默认时候是否选中
     */
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 是否排他
     * @return
     */
    public boolean isExclusive() {
        return isExclusive;
    }

    /**
     * 排他  选中之后其他为非选中状态
     */
    public void setIsExclusive(boolean isExclusive) {
        this.isExclusive = isExclusive;
    }

    @Override
    public String toString() {
        return caseText;
    }

}
