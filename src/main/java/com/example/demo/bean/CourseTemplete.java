package com.example.demo.bean;

public class CourseTemplete {
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
        private ValueBean teacher;
        private ValueBean courseCount;
        private ValueBean classRoom;
        private ValueBean courseSection;
        private ValueBean courseInfo;
        private ValueBean remark;

        public DataBean() {
        }

        public DataBean(ValueBean teacher, ValueBean courseCount, ValueBean classRoom, ValueBean courseSection, ValueBean courseInfo, ValueBean remark) {
            this.teacher = teacher;
            this.courseCount = courseCount;
            this.classRoom = classRoom;
            this.courseSection = courseSection;
            this.courseInfo = courseInfo;
            this.remark = remark;
        }

        public ValueBean getTeacher() {
            return teacher;
        }

        public void setTeacher(ValueBean teacher) {
            this.teacher = teacher;
        }

        public ValueBean getCourseCount() {
            return courseCount;
        }

        public void setCourseCount(ValueBean courseCount) {
            this.courseCount = courseCount;
        }

        public ValueBean getClassRoom() {
            return classRoom;
        }

        public void setClassRoom(ValueBean classRoom) {
            this.classRoom = classRoom;
        }

        public ValueBean getCourseSection() {
            return courseSection;
        }

        public void setCourseSection(ValueBean courseSection) {
            this.courseSection = courseSection;
        }

        public ValueBean getCourseInfo() {
            return courseInfo;
        }

        public void setCourseInfo(ValueBean courseInfo) {
            this.courseInfo = courseInfo;
        }

        public ValueBean getRemark() {
            return remark;
        }

        public void setRemark(ValueBean remark) {
            this.remark = remark;
        }

        public static class ValueBean{
            private String value;
            private String color;

            public ValueBean() {
            }

            public ValueBean(String value) {
                this.value = value;
            }

            public ValueBean(String value, String color) {
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

    }
}
