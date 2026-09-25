/* ModernFix Reforged addition, 2026-09-25. SPDX-License-Identifier: LGPL-3.0-or-later */
package org.embeddedt.modernfix.util;

import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.RejectedExecutionException;
import static org.junit.jupiter.api.Assertions.*;

class SingleThreadedWorkerServiceTest {
    @Test void nestedSubmissionCompletesWithoutDeadlock() throws Exception {
        var service = new SingleThreadedWorkerService();
        try {
            assertEquals(42, service.submit(() -> service.submit(() -> 42).get(2, TimeUnit.SECONDS))
                    .get(5, TimeUnit.SECONDS));
        } finally { service.shutdownNow(); }
    }

    @Test void workerCannotAcceptNewTasksAfterShutdown() throws Exception {
        var service = new SingleThreadedWorkerService();
        try {
            service.submit(() -> {
                service.shutdown();
                assertThrows(RejectedExecutionException.class, () -> service.execute(() -> fail("ran after shutdown")));
                assertThrows(NullPointerException.class, () -> service.execute(null));
            }).get(5, TimeUnit.SECONDS);
            assertTrue(service.awaitTermination(5, TimeUnit.SECONDS));
        } finally { service.shutdownNow(); }
    }
}
