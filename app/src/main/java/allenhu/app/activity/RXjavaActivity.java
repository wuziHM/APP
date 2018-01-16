package allenhu.app.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.util.StringUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RXjavaActivity extends BaseActivity {


    @BindView(R.id.tv_test)
    TextView
            tvTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        rxjavaTest();
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);


        rxJava2Test();


    }

    private void rxJava2Test() {
        final Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //执行一些其他操作
                //.............
                //执行完毕，触发回调，通知观察者
                e.onNext("我来发射数据");
            }
        });


        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            //观察者接收到通知,进行相关操作
            public void onNext(String aLong) {
                System.out.println("我接收到数据了");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);

    }

    private void rxjavaTest() {
//        Observable observable1 = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });
//
//        observable1.subscribe(new Action1<String>() {
//            @Override
//            public void call(String o) {
//                LogUtil.i("o:" + o);
//
//            }
//        });

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

//        Subscriber<Course> subscriber2 = new Subscriber<Course>() {
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
//            public void onNext(Course course) {
//                LogUtil.i(course.cName);
//            }
//        };
//
//        Observable.from(createStudents()).flatMap(new Func1<Student, Observable<Course>>() {
//            @Override
//            public Observable<Course> call(Student student) {
//                return Observable.from(student.getCourse());
//            }
//        }).subscribe(subscriber2);


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am " + i);
                }
                return Observable.fromIterable(list).delay(1, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.d("s:" + s);
            }
        });
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
