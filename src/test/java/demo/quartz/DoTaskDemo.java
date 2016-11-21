package demo.quartz;

import java.util.Date;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class DoTaskDemo {
    public static void main(String[] args) throws Exception {

        DoTaskDemo test = new DoTaskDemo();
        test.go();
    }

    public void go() throws Exception {
        // // 1、创建JobDetial对象
        JobDetailImpl jobDetail = new JobDetailImpl();
        // 设置工作项
        jobDetail.setJobClass(MyJob.class);
        jobDetail.setName("MyJob_1");
        jobDetail.setGroup("JobGroup_1");
        //
        // 2、创建Trigger对象
        // SimpleTriggerImpl strigger = new SimpleTriggerImpl();
        CronTriggerImpl strigger = new CronTriggerImpl();

        strigger.setName("Trigger_1");
        strigger.setGroup("Trigger_Group_1");
        strigger.setStartTime(new Date());
        // 设置重复停止时间，并销毁该Trigger对象
        // Calendar c = Calendar.getInstance();
        // c.setTimeInMillis(System.currentTimeMillis() + 1000 * 1L);
        // strigger.setEndTime(c.getTime());
        // strigger.setFireInstanceId("Trigger_1_id_001");
        // 设置重复间隔时间
        // strigger.setRepeatInterval(1000 * 1L);
        // 设置重复执行次数
        // strigger.setRepeatCount(10);
        strigger.setCronExpression("0/20 * * * * ?");

        // 3、创建Scheduler对象，并配置JobDetail和Trigger对象
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = sf.getScheduler();
            scheduler.scheduleJob(jobDetail, strigger);
            // 4、并执行启动、关闭等操作
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        // try {
        // //关闭调度器
        // scheduler.shutdown(true);
        // } catch (SchedulerException e) {
        // e.printStackTrace();
        // }
    }
}
