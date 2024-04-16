package tech.pantheon.triemap;

import static java.util.Objects.requireNonNull;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

record SomeValue<K, V>(@NonNull V value) implements Value<K, V> {

    @Override
    public V apply(@NonNull final K key, @Nullable final V currentValue) {
        return value;
    }

    public static <K, V> Value<K, V> from(@NonNull final V value) {
        return new SomeValue<>(requireNonNull(value));
    }
}
