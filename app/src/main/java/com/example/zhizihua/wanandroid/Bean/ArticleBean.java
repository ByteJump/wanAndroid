package com.example.zhizihua.wanandroid.Bean;

import java.util.List;

/**
 * Created by zhizihua on 2019/3/25.
 */

public class ArticleBean {

    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","author":"TeaOf","chapterId":142,"chapterName":"ConstraintLayout","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":8124,"link":"https://www.jianshu.com/p/7f111f0bdbd0","niceDate":"13小时前","origin":"","projectLink":"","publishTime":1553443889000,"superChapterId":54,"superChapterName":"5.+高新技术","tags":[],"title":"妙用ConstraintLayout的Circular positioning","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"开发的猫","chapterId":100,"chapterName":"RecyclerView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":8123,"link":"https://www.jianshu.com/p/1837a801e599","niceDate":"14小时前","origin":"","projectLink":"","publishTime":1553440323000,"superChapterId":54,"superChapterName":"5.+高新技术","tags":[],"title":"由旋转画廊，看自定义RecyclerView.LayoutManager","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"慕涵盛华","chapterId":153,"chapterName":"进程启动相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":8121,"link":"https://www.jianshu.com/p/4dfc0b67d92f","niceDate":"14小时前","origin":"","projectLink":"","publishTime":1553440094000,"superChapterId":153,"superChapterName":"framework","tags":[],"title":"Android系统启动过程分析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"慕涵盛华","chapterId":153,"chapterName":"进程启动相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":8122,"link":"https://www.jianshu.com/p/b158615cc2ad","niceDate":"14小时前","origin":"","projectLink":"","publishTime":1553440051000,"superChapterId":153,"superChapterName":"framework","tags":[],"title":"应用程序进程启动过程","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"ForgetSky","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"项目基于 Material Design + MVP +dagger2 + RxJava + Retrofit + Glide + greendao 等架构进行设计实现，极力打造一款 优秀的玩Android  https://www.wanandroid.com  客户端，是一个不错的Android应用开发学习参考项目","envelopePic":"https://www.wanandroid.com/blogimgs/796709d5-6238-4fc7-bcbd-6346ea43cf81.png","fresh":false,"id":8120,"link":"http://www.wanandroid.com/blog/show/2538","niceDate":"1天前","origin":"","projectLink":"https://github.com/ForgetSky/ForgetSkyWanAndroid","publishTime":1553342918000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"一款精致的玩Android客户端","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"digtal","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"Kotlin + MVP + Rxjava+ Retrofit2写一个玩Android客户端","envelopePic":"https://www.wanandroid.com/blogimgs/cf0d5bc9-50fa-4e8e-b2ff-4b8bc2b0c347.png","fresh":false,"id":8119,"link":"http://www.wanandroid.com/blog/show/2537","niceDate":"1天前","origin":"","projectLink":"https://github.com/digtal/WanAndroid","publishTime":1553342871000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Kotlin玩Android客户端","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"无心.","chapterId":307,"chapterName":"Apk诞生记","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8118,"link":"https://blog.csdn.net/huxin1875/article/details/87816465","niceDate":"1天前","origin":"","projectLink":"","publishTime":1553336778000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":" Android打包流程之资源管理","type":0,"userId":-1,"visible":1,"zan":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","author":"TeaOf","chapterId":142,"chapterName":"ConstraintLayout","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":8124,"link":"https://www.jianshu.com/p/7f111f0bdbd0","niceDate":"13小时前","origin":"","projectLink":"","publishTime":1553443889000,"superChapterId":54,"superChapterName":"5.+高新技术","tags":[],"title":"妙用ConstraintLayout的Circular positioning","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"开发的猫","chapterId":100,"chapterName":"RecyclerView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":8123,"link":"https://www.jianshu.com/p/1837a801e599","niceDate":"14小时前","origin":"","projectLink":"","publishTime":1553440323000,"superChapterId":54,"superChapterName":"5.+高新技术","tags":[],"title":"由旋转画廊，看自定义RecyclerView.LayoutManager","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"慕涵盛华","chapterId":153,"chapterName":"进程启动相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":8121,"link":"https://www.jianshu.com/p/4dfc0b67d92f","niceDate":"14小时前","origin":"","projectLink":"","publishTime":1553440094000,"superChapterId":153,"superChapterName":"framework","tags":[],"title":"Android系统启动过程分析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"慕涵盛华","chapterId":153,"chapterName":"进程启动相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":8122,"link":"https://www.jianshu.com/p/b158615cc2ad","niceDate":"14小时前","origin":"","projectLink":"","publishTime":1553440051000,"superChapterId":153,"superChapterName":"framework","tags":[],"title":"应用程序进程启动过程","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"ForgetSky","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"项目基于 Material Design + MVP +dagger2 + RxJava + Retrofit + Glide + greendao 等架构进行设计实现，极力打造一款 优秀的玩Android  https://www.wanandroid.com  客户端，是一个不错的Android应用开发学习参考项目","envelopePic":"https://www.wanandroid.com/blogimgs/796709d5-6238-4fc7-bcbd-6346ea43cf81.png","fresh":false,"id":8120,"link":"http://www.wanandroid.com/blog/show/2538","niceDate":"1天前","origin":"","projectLink":"https://github.com/ForgetSky/ForgetSkyWanAndroid","publishTime":1553342918000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"一款精致的玩Android客户端","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"digtal","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"Kotlin + MVP + Rxjava+ Retrofit2写一个玩Android客户端","envelopePic":"https://www.wanandroid.com/blogimgs/cf0d5bc9-50fa-4e8e-b2ff-4b8bc2b0c347.png","fresh":false,"id":8119,"link":"http://www.wanandroid.com/blog/show/2537","niceDate":"1天前","origin":"","projectLink":"https://github.com/digtal/WanAndroid","publishTime":1553342871000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Kotlin玩Android客户端","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"无心.","chapterId":307,"chapterName":"Apk诞生记","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8118,"link":"https://blog.csdn.net/huxin1875/article/details/87816465","niceDate":"1天前","origin":"","projectLink":"","publishTime":1553336778000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":" Android打包流程之资源管理","type":0,"userId":-1,"visible":1,"zan":0}]
         */

        private int curPage;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * author : TeaOf
             * chapterId : 142
             * chapterName : ConstraintLayout
             * collect : false
             * courseId : 13
             * desc :
             * envelopePic :
             * fresh : true
             * id : 8124
             * link : https://www.jianshu.com/p/7f111f0bdbd0
             * niceDate : 13小时前
             * origin :
             * projectLink :
             * publishTime : 1553443889000
             * superChapterId : 54
             * superChapterName : 5.+高新技术
             * tags : []
             * title : 妙用ConstraintLayout的Circular positioning
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private String projectLink;
            private long publishTime;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
}
