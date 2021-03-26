package in.shramishkafle;

import in.shramishkafle.util.Const;
import in.shramishkafle.util.EnvVarToFile;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;


@Mojo(name="make")
public class TestMGMakerMojo extends AbstractMojo{
    public void execute() throws MojoExecutionException, MojoFailureException {
        verifyEnvVariableExists(Const.suiteName);
        verifyEnvVariableExists(Const.ReportLocation);
        verifyEnvVariableExists(Const.remoteURL);
        verifyEnvVariableExists(Const.baseURL);
        verifyEnvVariableExists(Const.internal);
        verifyEnvVariableExists(Const.parallel);
        verifyEnvVariableExists(Const.thread_count);
        verifyEnvVariableExists(Const.tests);
        verifyEnvVariableExists(Const.browsers);
        verifyEnvVariableExists(Const.packageName);

        EnvVarToFile envVarToFile = new EnvVarToFile();
        envVarToFile.write();
    }
    public void verifyEnvVariableExists(String var){
        if(System.getenv(var) == null || "".equals(System.getenv(var)))
            throw new NullPointerException("Env variable " + var + " is mandatory.");

    }
}