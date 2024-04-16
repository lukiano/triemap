package tech.pantheon.triemap;

import static java.util.Objects.requireNonNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

final class ComputeValue<K, V> implements Value<K, V> {

    private final BiFunction<? super K, ? super V, ? extends V> function;

    private ComputeValue(@NonNull final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        function = requireNonNull(remappingFunction);
    }

    @Override
    public V apply(@NonNull final K key, @Nullable final V currentValue) {
        return function.apply(key, currentValue);
    }

    public static <K, V> Value<K, V> from(
            @NonNull final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return new ComputeValue<>(remappingFunction);
    }

    public static <K, V> Value<K, V> from(
            @NonNull Function<? super K, ? extends V> mappingFunction) {
        return from((k, v) -> mappingFunction.apply(k));
    }

    public static <K, V> Value<K, V> merge(
            @NonNull final BiFunction<? super V, ? super V, ? extends V> remappingFunction, @NonNull final V value) {
        return from((k, v) -> v == null ? value : remappingFunction.apply(v, value));
    }
}
