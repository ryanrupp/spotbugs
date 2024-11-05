package nullnessAnnotations;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import org.apache.commons.lang3.Validate;

import javax.annotation.CheckForNull;
import java.io.File;
import java.util.Objects;

public class TestNonNull7 {
    /**
     * Issue <a href="https://github.com/spotbugs/spotbugs/issues/456">#456</a>.
     */
    public void objectsrequireNonNull(File rootFile) {
        for (File subFile : Objects.requireNonNull(rootFile.listFiles())) {
            System.out.println(subFile.getName());
        }
    }

    public void objectsrequireNonNullFalse(File rootFile) {
        for (File subFile : requireNonNull(rootFile.listFiles())) {
            System.out.println(subFile.getName());
        }
    }

    private <T> T requireNonNull(T obj) {
        return obj;
    }

    public void objectsNonNull(File rootFile) {
        File[] files = rootFile.listFiles();
        if (Objects.nonNull(files)) {
            for (File subFile : files) {
                System.out.println(subFile.getName());
            }
        }
    }

    public void verifyverifyNotNull(File rootFile) {
        for (File subFile : Verify.verifyNotNull(rootFile.listFiles())) {
            System.out.println(subFile.getName());
        }
    }

    public void validatenotNull(File rootFile) {
        for (File subFile : Validate.notNull(rootFile.listFiles())) {
            System.out.println(subFile.getName());
        }
    }

    public void preconditionsNotNull(File rootFile) {
        for (File subFile : Preconditions.checkNotNull(rootFile.listFiles())) {
            System.out.println(subFile.getName());
        }
    }

    public void multipleStatementsFails() {
        String result = methodThatCanReturnNull();
        result = Objects.requireNonNull(result); // NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE reported on this line
        System.out.println(result.length());
    }

    public void nullCheckWithBranchingWorks() {
        String result = methodThatCanReturnNull();
        if (result == null) {
            throw new IllegalArgumentException("This works!");
        }

        System.out.println(result.length());
    }

    @CheckForNull
    private String methodThatCanReturnNull() {
        return null;
    }
}
