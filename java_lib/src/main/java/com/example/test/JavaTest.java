package com.example.test;

import com.example.Utils;
import com.example.design.Factory.Child;
import com.example.design.Factory.abs.HotRestaurant;
import com.example.design.Factory.abs.SweetRestaurant;
import com.example.design.Factory.method.BreadRestaurant;
import com.example.design.Factory.method.FishRestaurant;
import com.example.design.Factory.method.MRestaurant;
import com.example.design.Factory.method.NoodlesRestaurant;
import com.example.design.Factory.simple.Restaurant;
import com.example.design.observer.Customer1;
import com.example.design.observer.Customer2;
import com.example.design.observer.MilkProvider;
import com.example.design.proxy.Panqiaoyun;
import com.example.design.proxy.ProxyWang;
import com.example.other.IdcardValidator;
import com.example.strategy.Context;
import com.example.strategy.RechargeTypeEnum;
import com.example.utils.JDomDemo;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：燕青 $ on 16/7/22 16:17
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class JavaTest extends TestCase {
    public void testOb() {
        MilkProvider provider = new MilkProvider();
        Customer1 customer1 = new Customer1();
        Customer2 customer2 = new Customer2();
        provider.addObserver(customer1);
        provider.addObserver(customer2);
        provider.milkProduced();
    }

    public void testSuper() {
        Child child = new Child();
        child.say();
    }

    public void testProxy() {
        ProxyWang wang = new ProxyWang();
        wang.makeEyesWithMan();
        wang.playWithMan();

        wang = new ProxyWang(new Panqiaoyun());
        wang.playWithMan();
        wang.makeEyesWithMan();
    }

    public void testSimpleFactory() {
        Restaurant restaurant = new Restaurant();
        restaurant.cooking(Restaurant.BREAD).getFood();
        restaurant.cooking(Restaurant.FISH).getFood();
        restaurant.cooking(Restaurant.NOODLES).getFood();

    }

    public void testMethodFactory() {
        MRestaurant restaurant = new FishRestaurant();
        restaurant.cooking().getFood();

        restaurant = new BreadRestaurant();
        restaurant.cooking().getFood();

        restaurant = new NoodlesRestaurant();
        restaurant.cooking().getFood();
    }

    public void testAbstractFactory() {
        com.example.design.Factory.abs.Restaurant restaurant = new SweetRestaurant();
        restaurant.getBread().getFood();
        restaurant.getFish().getFood();
        restaurant.getNoodles().getFood();

        restaurant = new HotRestaurant();
        restaurant.getBread().getFood();
        restaurant.getNoodles().getFood();
        restaurant.getFish().getFood();
    }

    public void testPattern() {
        String a = "12345678939876098X";
        String b = "123456789100917";
        System.out.println(Utils.isIdCardNum(a));
        System.out.println(Utils.isIdCardNum(b));
    }

    public void testIdCard() {

//        String idcard15 = "142431199001145";//15位
        String idcard18 = "140196199909094237";//18位
//        String idcard18 = "36232219930109002X";//18位
        IdcardValidator iv = new IdcardValidator();
        idcard18 = idcard18.substring(0, 17);


        System.out.println(idcard18);
//        System.out.println(iv.isValidatedAllIdcard(idcard18));
        String[] ss = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "x", "X"};
//        System.out.println(iv.isValidatedAllIdcard(idcard15));
        for (String s : ss) {
            String str = idcard18 + s;
            System.out.println(iv.isValidatedAllIdcard(str));
        }
    }

    public void testStrategy() {

        Context context = new Context();
        // 网银充值100 需要付多少
        Double money = context.calRecharge(100D, RechargeTypeEnum.E_BANK.value());
        System.out.println(money);

        // 商户账户充值100 需要付多少
        Double money2 = context.calRecharge(100D, RechargeTypeEnum.BUSI_ACCOUNTS.value());
        System.out.println(money2);

        // 手机充值100 需要付多少
        Double money3 = context.calRecharge(100D,
                RechargeTypeEnum.MOBILE.value());
        System.out.println(money3);

        // 充值卡充值100 需要付多少
        Double money4 = context.calRecharge(100D,
                RechargeTypeEnum.CARD_RECHARGE.value());
        System.out.println(money4);
    }

    public void testBoolean() throws UnsupportedEncodingException {

//        String urlString = URLEncoder.encode("鄂ARF465", "utf-8");
//        String s = "http://che.weyee.com/?id=" + urlString;
//        System.out.println(s);

        String path = "/Users/minhu/Documents/E/weyee/program/POS/app/src/main/res/values/dimens.xml";
        JDomDemo domDemo = new JDomDemo();
//        domDemo.createXml(path);

        domDemo.parserXml(path);
    }


    public void testFlow() {
        methodA(1);
        methodB(1);
        methodC(1);
        methodD(1);
    }

    private void methodA(int i) {
        if (i == 1) {
            return;
        }
        System.out.println("aaaaaaaaa");
    }

    private void methodB(int i) {
        System.out.println("bbbbbbbbbbbb");
    }

    private void methodC(int i) {
        System.out.println("cccccccccc");

    }

    private void methodD(int i) {
        System.out.println("dddddddd");

        System.out.println(formatDouble(32320920190.09088d));


    }

    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(d);
    }

    public void testIO() {

        File file = new File("test.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                System.out.println("====");
            }

            FileInputStream inputStream = new FileInputStream(file);
            byte[] buf = new byte[10];
            int len;
            while ((len = inputStream.read(buf)) != -1) {

                System.out.println("11111");
                System.out.println(new String(buf, 0, len));
            }

            System.out.println("22222");

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //开始
    public void testProblem() {
        int t = 1;
        Problem[] problems = createProblem();

        for (Problem.ANSWER answer1 : Problem.ANSWER.values()) {
            problems[0].setAnswer(answer1);
            for (Problem.ANSWER answer2 : Problem.ANSWER.values()) {
                problems[1].setAnswer(answer2);
                switch (answer2) {
                    case A:
                        problems[4].setAnswer(Problem.ANSWER.C);
                        break;
                    case B:
                        problems[4].setAnswer(Problem.ANSWER.D);
                        break;
                    case C:
                        problems[4].setAnswer(Problem.ANSWER.A);
                        break;
                    case D:
                        problems[4].setAnswer(Problem.ANSWER.B);
                        break;
                }
                for (Problem.ANSWER answer3 : Problem.ANSWER.values()) {
                    problems[2].setAnswer(answer3);
                    for (Problem.ANSWER answer4 : Problem.ANSWER.values()) {
                        problems[3].setAnswer(answer4);
                        for (Problem.ANSWER answer6 : Problem.ANSWER.values()) {
                            problems[5].setAnswer(answer6);
                            for (Problem.ANSWER answer7 : Problem.ANSWER.values()) {
                                problems[6].setAnswer(answer7);
                                for (Problem.ANSWER answer8 : Problem.ANSWER.values()) {
                                    problems[7].setAnswer(answer8);
                                    for (Problem.ANSWER answer9 : Problem.ANSWER.values()) {
                                        problems[8].setAnswer(answer9);
                                        for (Problem.ANSWER answer10 : Problem.ANSWER.values()) {
                                            problems[9].setAnswer(answer10);
                                            if (!judge(problems)) {
                                                continue;
                                            } else {
                                                System.out.print("答案:" + t + "----->");
                                                for (int j = 0; j < problems.length; j++) {
                                                    System.out.print(problems[j].getAnswer().name() + " ");
                                                }
                                                t++;
                                                System.out.println();
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    private boolean judge(Problem[] problems) {

        boolean flag = judgeP2(problems)
                && judgeP3(problems)
                && judgeP4(problems)
                && judgeP5(problems)
                && judgeP6(problems)
                && judgeP7(problems)
                && judgeP8(problems)
                && judgeP9(problems)
                && judgeP10(problems);
        return flag;
    }

    /**
     * 第五题的答案是什么
     *
     * @param problems
     * @return
     */
    private boolean judgeP2(Problem[] problems) {
        if (problems[1].getAnswer().equals(Problem.ANSWER.A) && problems[4].getAnswer().equals(Problem.ANSWER.C)) {
            return true;
        }

        if (problems[1].getAnswer().equals(Problem.ANSWER.B) && problems[4].getAnswer().equals(Problem.ANSWER.D)) {
            return true;
        }

        if (problems[1].getAnswer().equals(Problem.ANSWER.C) && problems[4].getAnswer().equals(Problem.ANSWER.A)) {
            return true;
        }

        if (problems[1].getAnswer().equals(Problem.ANSWER.D) && problems[4].getAnswer().equals(Problem.ANSWER.B)) {
            return true;
        }
        return false;
    }


    /**
     * 通过第三题可以知道 2、3、4、6这四道题里有3道题的答案是一样的
     *
     * @return
     */
    private boolean judgeP3(Problem[] problems) {
        //2、3、4相同  6不同
        Problem problem2 = problems[1];
        Problem problem3 = problems[2];
        Problem problem4 = problems[3];
        Problem problem6 = problems[5];
        if (isEquals(problem6, problem3, problem4) && !isEquals(problem2, problem6)) {
            //如果是第二题不一样,那么第三题的答案就是C，那么6、3、4的答案都必须是C  第二题的答案不能是C
            if (!problem2.getAnswer().equals(Problem.ANSWER.C)
                    && problem3.getAnswer().equals(Problem.ANSWER.C)
                    && problem4.getAnswer().equals(Problem.ANSWER.C)
                    && problem6.getAnswer().equals(Problem.ANSWER.C))
                return true;
        }

        if (isEquals(problem2, problem3, problem6) && !isEquals(problem2, problem4))
            if (problem2.getAnswer().equals(Problem.ANSWER.D)
                    && problem3.getAnswer().equals(Problem.ANSWER.D)
                    && problem6.getAnswer().equals(Problem.ANSWER.D)
                    && !problem4.getAnswer().equals(Problem.ANSWER.D))
                return true;

        //如果是第三题不一样，那么第三题的答案就是A，那么2，4，6必须相同，而且不能是A
        if (isEquals(problem2, problem6, problem4) && !isEquals(problem2, problem3))
            if (problem3.getAnswer().equals(Problem.ANSWER.A)
                    && !problem2.getAnswer().equals(Problem.ANSWER.A)
                    && !problem6.getAnswer().equals(Problem.ANSWER.A)
                    && !problem4.getAnswer().equals(Problem.ANSWER.A))
                return true;

        //如果是第六题的答案不一样，那么第三题的答案就是B，2,3,4必须相等，必须都是B
        if (isEquals(problem6, problem3, problem4) && !isEquals(problem2, problem6))
            if (!problem6.getAnswer().equals(Problem.ANSWER.B)
                    && problem2.getAnswer().equals(Problem.ANSWER.B)
                    && problem6.getAnswer().equals(Problem.ANSWER.B)
                    && problem3.getAnswer().equals(Problem.ANSWER.B))
                return true;

        return false;
    }


    /**
     * 哪两个题目答案相同1与5    2与7   1与9  6与10
     *
     * @param problems
     * @return
     */
    private boolean judgeP4(Problem[] problems) {
        if (isEquals(problems[0], problems[4])
                && !isEquals(problems[1], problems[6])
                && !isEquals(problems[0], problems[8])
                && !isEquals(problems[5], problems[9])) {
            return true;
        }
        if (!isEquals(problems[0], problems[4])
                && isEquals(problems[1], problems[6])
                && !isEquals(problems[0], problems[8])
                && !isEquals(problems[5], problems[9])) {
            return true;
        }
        if (!isEquals(problems[0], problems[4])
                && !isEquals(problems[1], problems[6])
                && isEquals(problems[0], problems[8])
                && !isEquals(problems[5], problems[9])) {
            return true;
        }
        if (!isEquals(problems[0], problems[4])
                && !isEquals(problems[1], problems[6])
                && !isEquals(problems[0], problems[8])
                && isEquals(problems[5], problems[9])) {
            return true;
        }

        return false;
    }

    /**
     * 4  7  8  9哪道题与第五题相同
     *
     * @param problems
     * @return
     */
    private boolean judgeP5(Problem[] problems) {
        //第五题和第四题相同，那么第五题答案就是B，那么第四题答案也必须是B
        if (isEquals(problems[4], problems[3])
                && !isEquals(problems[4], problems[6])
                && !isEquals(problems[4], problems[7])
                && !isEquals(problems[4], problems[8])
                && problems[4].getAnswer().equals(Problem.ANSWER.B)
                && problems[3].getAnswer().equals(Problem.ANSWER.B)) {
            return true;

        }
        if (isEquals(problems[4], problems[6])
                && !isEquals(problems[4], problems[3])
                && !isEquals(problems[4], problems[7])
                && !isEquals(problems[4], problems[8])

                && problems[4].getAnswer().equals(Problem.ANSWER.D)
                && problems[6].getAnswer().equals(Problem.ANSWER.D)) {
            return true;

        }
        if (isEquals(problems[4], problems[7])
                && !isEquals(problems[4], problems[6])
                && !isEquals(problems[4], problems[3])
                && !isEquals(problems[4], problems[8])
                && problems[4].getAnswer().equals(Problem.ANSWER.A)
                && problems[7].getAnswer().equals(Problem.ANSWER.A)) {
            return true;

        }
        if (isEquals(problems[4], problems[8])
                && !isEquals(problems[4], problems[6])
                && !isEquals(problems[4], problems[7])
                && !isEquals(problems[4], problems[3])
                && problems[4].getAnswer().equals(Problem.ANSWER.C)
                && problems[9].getAnswer().equals(Problem.ANSWER.C)) {
            return true;

        }
        return false;
    }

    /**
     * 哪个选项中的两个题目答案与第8题相同
     * <p>
     * A:2,4
     * B:1,6
     * C:3,10
     * D:5,9
     *
     * @param problems
     * @return
     */
    private boolean judgeP6(Problem[] problems) {

        if (isEquals(problems[7], problems[1], problems[3])
                && (!isEquals(problems[7], problems[0]) || !isEquals(problems[7], problems[5]))
                && (!isEquals(problems[7], problems[2]) || !isEquals(problems[7], problems[9]))
                && (!isEquals(problems[7], problems[4]) || !isEquals(problems[7], problems[8]))) {
            return true;

        }

        if (isEquals(problems[7], problems[0], problems[5])
                && (!isEquals(problems[7], problems[1]) || !isEquals(problems[7], problems[3]))
                && (!isEquals(problems[7], problems[2]) || !isEquals(problems[7], problems[9]))
                && (!isEquals(problems[7], problems[4]) || !isEquals(problems[7], problems[8]))) {
            return true;

        }

        if (isEquals(problems[7], problems[2], problems[9])
                && (!isEquals(problems[7], problems[0]) || !isEquals(problems[7], problems[5]))
                && (!isEquals(problems[7], problems[1]) || !isEquals(problems[7], problems[3]))
                && (!isEquals(problems[7], problems[4]) || !isEquals(problems[7], problems[8]))) {
            return true;

        }

        if (isEquals(problems[7], problems[4], problems[8])
                && (!isEquals(problems[7], problems[0]) || !isEquals(problems[7], problems[5]))
                && (!isEquals(problems[7], problems[2]) || !isEquals(problems[7], problems[9]))
                && (!isEquals(problems[7], problems[1]) || !isEquals(problems[7], problems[3]))) {
            return true;

        }


        return false;
    }


    /**
     * 10个题目中选择的字母最少的是哪个
     *
     * @param problems
     * @return
     */
    private boolean judgeP7(Problem[] problems) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        for (Problem problem : problems) {
            switch (problem.getAnswer()) {
                case A:
                    countA++;
                    break;
                case B:
                    countB++;
                    break;
                case C:
                    countC++;
                    break;
                case D:
                    countD++;
                    break;
            }
        }
        int min = Math.min(Math.min(countA, countB), Math.min(countC, countD));
        if (problems[6].getAnswer().equals(Problem.ANSWER.A) && min == countC) {
            return true;
        }
        if (problems[6].getAnswer().equals(Problem.ANSWER.B) && min == countB) {
            return true;
        }
        if (problems[6].getAnswer().equals(Problem.ANSWER.C) && min == countA) {
            return true;
        }
        if (problems[6].getAnswer().equals(Problem.ANSWER.D) && min == countD) {
            return true;
        }
        return false;
    }

    /**
     * 2 5 7 10四个题目中有一个与问题1的答案不相连
     *
     * @param problems
     * @return
     */
    private boolean judgeP8(Problem[] problems) {
        //如果问题2与问题1不相连，那么其他的几个必须相连
        if (!isLinked(problems[1], problems[0])
                && isLinked(problems[0], problems[4])
                && isLinked(problems[0], problems[6])
                && isLinked(problems[0], problems[9]))
            return true;

        if (!isLinked(problems[0], problems[4])
                && isLinked(problems[0], problems[1])
                && isLinked(problems[0], problems[6])
                && isLinked(problems[0], problems[9]))
            return true;

        if (!isLinked(problems[6], problems[0])
                && isLinked(problems[0], problems[4])
                && isLinked(problems[0], problems[1])
                && isLinked(problems[0], problems[9]))
            return true;

        if (!isLinked(problems[9], problems[0])
                && isLinked(problems[0], problems[4])
                && isLinked(problems[0], problems[6])
                && isLinked(problems[0], problems[1]))
            return true;

        return false;
    }


    /**
     * 根据题目9做判断
     *
     * @param problems
     * @return
     */
    private boolean judgeP9(Problem[] problems) {
        //如果第一题和第六题答案相同  那么第x题和第5题答案不相同，
        //x只能是2、6、9、10中的
        if (problems[0].getAnswer().equals(problems[5].getAnswer())) {
            if (!problems[4].getAnswer().equals(problems[1].getAnswer())
                    || !problems[4].getAnswer().equals(problems[5].getAnswer())
                    || !problems[4].getAnswer().equals(problems[8].getAnswer())
                    || !problems[4].getAnswer().equals(problems[9].getAnswer())) {
                return true;
            }
        }

        //如果第一题和第六题答案不相同，那么第x题和第5题答案相同
        if (!isEquals(problems[0], problems[5])) {
            if (isEquals(problems[4], problems[5])
                    && !isEquals(problems[4], problems[9])
                    && !isEquals(problems[4], problems[1])
                    && !isEquals(problems[4], problems[8])) {
                return true;

            }
            if (isEquals(problems[4], problems[9])
                    && !isEquals(problems[4], problems[5])
                    && !isEquals(problems[4], problems[1])
                    && !isEquals(problems[4], problems[8])) {
                return true;

            }
            if (isEquals(problems[4], problems[1])
                    && !isEquals(problems[4], problems[5])
                    && !isEquals(problems[4], problems[9])
                    && !isEquals(problems[4], problems[8])) {
                return true;

            }
            if (isEquals(problems[4], problems[8])
                    && !isEquals(problems[4], problems[5])
                    && !isEquals(problems[4], problems[9])
                    && !isEquals(problems[4], problems[1])) {
                return true;

            }
        }
        return false;
    }

    private boolean judgeP10(Problem[] problems) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;

        for (Problem problem : problems) {
            switch (problem.getAnswer()) {
                case A:
                    countA++;
                    break;
                case B:
                    countB++;
                    break;
                case C:
                    countC++;
                    break;
                case D:
                    countD++;
                    break;
            }
        }
        int max = Math.max(Math.max(countA, countB), Math.max(countC, countD));
        int min = Math.min(Math.min(countA, countB), Math.min(countC, countD));
        if (max - min == 1 || max - min == 2 || max - min == 3 || max - min == 4) {
            return true;
        }
        return false;
    }

    /**
     * 判断两道题的答案是否相同
     *
     * @param problemA
     * @param problemB
     * @return
     */
    private boolean isEquals(Problem problemA, Problem problemB) {
        return problemA.getAnswer().equals(problemB.getAnswer());
    }

    /**
     * 三个是不是相同
     *
     * @param problemA
     * @param problemB
     * @param problemC
     * @return
     */
    private boolean isEquals(Problem problemA, Problem problemB, Problem problemC) {
        return problemA.getAnswer().equals(problemB.getAnswer()) && problemA.getAnswer().equals(problemC.getAnswer());
    }

    /**
     * 两个问题的答案是否相连
     *
     * @param problemA
     * @param problemB
     * @return
     */
    private boolean isLinked(Problem problemA, Problem problemB) {

        char a = problemA.getAnswer().name().charAt(0);
        char b = problemB.getAnswer().name().charAt(0);
        int t = Math.abs(a - b);
        return t == 1;

//        switch (problemA.getAnswer()) {
//            case A:
//                if (problemB.getAnswer().equals(Problem.ANSWER.B)) {
//                    return true;
//                }
//                break;
//            case B:
//                if (problemB.getAnswer().equals(Problem.ANSWER.A) || problemB.getAnswer().equals(Problem.ANSWER.C)) {
//                    return true;
//                }
//                break;
//
//            case C:
//                if (problemB.getAnswer().equals(Problem.ANSWER.B) || problemB.getAnswer().equals(Problem.ANSWER.D)) {
//                    return true;
//                }
//                break;
//
//            case D:
//                if (problemB.getAnswer().equals(Problem.ANSWER.C)) {
//                    return true;
//                }
//                break;
//        }
//
//        return false;

    }//结束

    public Problem[] createProblem() {
        Problem[] problems = new Problem[10];
        for (int i = 0; i < 10; i++) {
            problems[i] = new Problem();
            problems[i].setAnswer(Problem.ANSWER.A);
        }

        return problems;
    }


    public void testPatterns() {
        String str = "000007020300";
//        String pattern = "^(\\d{3})-(\\d{3,8})$";
//        String pattern = "\\d{3}-\\d{3,8}";
//        String pattern = "^(0[1-9]|1[0-2]|[0-9])-(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[0-9])$";
        String pattern = "\\d{10}00$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }

    public void testArrange() {
//        Arrange arrange = new Arrange();
        List<Character> data = new ArrayList<Character>();
        data.add('a');
        data.add('b');
        data.add('c');
        data.add('d');

        //输出A(n,n)的全排列
//        for (int i = 1; i <= data.size(); i++)
        arrangeSelect(data, new ArrayList<Character>(), 4);

    }


    /**
     * 计算A(n,k) 排列计算
     *
     * @param data
     * @param target
     * @param k
     */
    public <E> void arrangeSelect(List<E> data, List<E> target, int k) {
        List<E> copyData;
        List<E> copyTarget;

        System.out.println("k=" + k + "    data.size=" + data.size());
        if (target.size() == k) {
            for (E i : target)
                System.out.print(i);
            System.out.println();
        }

        for (int i = 0; i < data.size(); i++) {
            copyData = new ArrayList<E>(data);
            copyTarget = new ArrayList<E>(target);

            copyTarget.add(copyData.get(i));
            copyData.remove(i);

            arrangeSelect(copyData, copyTarget, k);
        }
    }


}
