package org.jrl.trace.otel;

import io.opentelemetry.context.Scope;
import org.jrl.trace.JrlScope;

/**
* otel scope 扩展
* @author JerryLong
*/
public class JrlOtelScope implements JrlScope {

    private final Scope scope;

    public JrlOtelScope(Scope scope) {
        this.scope = scope;
    }

    @Override
    public void close() {
        this.scope.close();
    }
}
