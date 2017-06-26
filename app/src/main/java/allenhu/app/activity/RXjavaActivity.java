package allenhu.app.activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.util.LogUtil;
import allenhu.app.util.StringUtils;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class RXjavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        rxjavaTest();

    }

    private void rxjavaTest() {
        Observable observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        observable1.subscribe(new Action1<String>() {
            @Override
            public void call(String o) {
                LogUtil.i("o:" + o);

            }
        });

//        observable1.subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                LogUtil.i(s);
//                ToastUtils.ToastMessage(RXjavaActivity.this, s);
//            }
//
//        });


//        Observable.from(new String[]{"www.baidu.com"})
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<String, Bitmap>() {
//                    @Override
//                    public Bitmap call(String s) {
//                        Resources res = getResources();
//                        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);
//                        return bmp;
//                    }
//                })
//                .subscribe(new Observer<Bitmap>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Bitmap bitmap) {
//                        LinearLayout.LayoutParams relLayoutParams
//                                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                        ImageView imageView = new ImageView(RXjavaActivity.this);
//                        imageView.setImageBitmap(bitmap);
//                        addContentView(imageView, relLayoutParams);
//                    }
//                });

        Subscriber<Course> subscriber2 = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                LogUtil.i(course.cName);
            }
        };

        Observable.from(createStudents()).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourse());
            }
        }).subscribe(subscriber2);

    }

    private List<Student> createStudents() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(createStudent());
        }
        return list;
    }

    private Student createStudent() {
        Student student = new Student();
        student.setName("name:" + StringUtils.getRandomString(3));
        student.setCourse(new Course[]{new Course("C1:" + StringUtils.getRandomString(3))
                , new Course("C2:" + StringUtils.getRandomString(3))
                , new Course("C3:" + StringUtils.getRandomString(3))});
        return student;
    }


    class Student {
        String name;
        Course[] course;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Course[] getCourse() {
            return course;
        }

        public void setCourse(Course[] course) {
            this.course = course;
        }
    }

    class Course {

        String cName;

        public Course(String cName) {
            this.cName = cName;
        }
    }

}
