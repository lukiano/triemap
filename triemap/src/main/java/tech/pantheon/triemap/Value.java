package tech.pantheon.triemap;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

sealed interface Value<K, V> permits ComputeValue, SomeValue {

    V apply(@NonNull K key, @Nullable V currentValue);
}
