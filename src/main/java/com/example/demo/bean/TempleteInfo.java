package com.example.demo.bean;

public class TempleteInfo {

    /**
     * touser : OPENID
     * template_id : ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY
     * url : http://weixin.qq.com/download
     * topcolor : #FF0000
     * data : {"courseDate":{"value":"黄先生","color":"#173177"},"course":{"value":"06月07日 19时24分","color":"#173177"},"startTime":{"value":"0426","color":"#173177"},"endTime":{"value":"消费","color":"#173177"},"remark":{"value":"人民币260.00元","color":"#173177"}}
     */

    private String touser;
    private String template_id;
    private String url;
    private String topcolor;
    private MiniProgram miniprogram;
    private DataBean data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MiniProgram{
        private String appid;
        private String pagepath;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
    }

    public static class DataBean {
        /**
         * courseDate : {"value":"黄先生","color":"#173177"}
         * course : {"value":"06月07日 19时24分","color":"#173177"}
         * startTime : {"value":"0426","color":"#173177"}
         * endTime : {"value":"消费","color":"#173177"}
         * remark : {"value":"人民币260.00元","color":"#173177"}
         */

        private CourseDateBean courseDate;
        private CourseBean course;
        private StartTimeBean startTime;
        private EndTimeBean endTime;
        private RemarkBean remark;

        public CourseDateBean getCourseDate() {
            return courseDate;
        }

        public void setCourseDate(CourseDateBean courseDate) {
            this.courseDate = courseDate;
        }

        public CourseBean getCourse() {
            return course;
        }

        public void setCourse(CourseBean course) {
            this.course = course;
        }

        public StartTimeBean getStartTime() {
            return startTime;
        }

        public void setStartTime(StartTimeBean startTime) {
            this.startTime = startTime;
        }

        public EndTimeBean getEndTime() {
            return endTime;
        }

        public void setEndTime(EndTimeBean endTime) {
            this.endTime = endTime;
        }

        public RemarkBean getRemark() {
            return remark;
        }

        public void setRemark(RemarkBean remark) {
            this.remark = remark;
        }

        public static class CourseDateBean {


            /**
             * value : 黄先生
             * color : #173177
             */

            private String value;
            private String color;
            public CourseDateBean(String value) {
                this.value = value;
            }

            public CourseDateBean(String value, String color) {
                this.value = value;
                this.color = color;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class CourseBean {
            /**
             * value : 06月07日 19时24分
             * color : #173177
             */

            private String value;
            private String color;

            public CourseBean(String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class StartTimeBean {
            /**
             * value : 0426
             * color : #173177
             */

            private String value;
            private String color;

            public StartTimeBean(String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class EndTimeBean {
            public EndTimeBean(String value) {
                this.value = value;
            }

            /**
             * value : 消费
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class RemarkBean {
            public RemarkBean(String value) {
                this.value = value;
            }

            /**
             * value : 人民币260.00元
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
    }
}
