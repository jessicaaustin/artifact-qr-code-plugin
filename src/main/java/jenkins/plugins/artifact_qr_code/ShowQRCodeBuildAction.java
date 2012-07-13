package jenkins.plugins.artifact_qr_code;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.InvisibleAction;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.model.listeners.RunListener;

import java.util.Collections;
import java.util.List;

/**
 * Show the artifacts on a build as an action.  This means that it will
 * only work for builds that have been run after this plugin was added.
 */
public class ShowQRCodeBuildAction extends InvisibleAction {
    private AbstractBuild<?, ?> build;

    public ShowQRCodeBuildAction(AbstractBuild<?, ?> build) {
        this.build = build;
    }

    public List getArtifacts() {
        List artifacts = build.getArtifacts();

        if (artifacts != null) {
            return artifacts;
        }

        return Collections.EMPTY_LIST;
    }

    public String getBuildUrl() {
        return build.getUrl();
    }

    @Extension
    public static final class RunListenerImpl extends RunListener<AbstractBuild<?, ?>> {

        @Override
        public void onCompleted(AbstractBuild<?, ?> r, TaskListener listener) {
            r.addAction(new ShowQRCodeBuildAction(r));
        }
    }
}
