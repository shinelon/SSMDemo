package demo.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * 执行的任务类 禁止并发执行多个相同定义的JobDetail(grop 和 name 相同)
 */
@DisallowConcurrentExecution
public class MyJob implements Job {

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println("job:" + arg0.getJobDetail().getKey() + "|" + sdf.format(new Date()));
    }

}