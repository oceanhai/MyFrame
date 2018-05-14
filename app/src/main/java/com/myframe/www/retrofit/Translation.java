package com.myframe.www.retrofit;

/**
 * Created by wuhai on 2018/5/11.
 */

public class Translation {

    /**
     * content : {"vendor":"ciba","from":"en-EU","to":"zh-CN","err_no":0,"out":"示例"}
     * status : 1
     */
    private ContentEntity content;
    private int status;

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ContentEntity getContent() {
        return content;
    }

    public int getStatus() {
        return status;
    }

    public class ContentEntity {
        /**
         * vendor : ciba
         * from : en-EU
         * to : zh-CN
         * err_no : 0
         * out : 示例
         */
        private String vendor;
        private String from;
        private String to;
        private int err_no;
        private String out;

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public void setErr_no(int err_no) {
            this.err_no = err_no;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public String getVendor() {
            return vendor;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public int getErr_no() {
            return err_no;
        }

        public String getOut() {
            return out;
        }

        @Override
        public String toString() {
            return "ContentEntity{" +
                    "vendor='" + vendor + '\'' +
                    ", from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", err_no=" + err_no +
                    ", out='" + out + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Translation{" +
                "content=" + content +
                ", status=" + status +
                '}';
    }
}
