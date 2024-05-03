package com.example.apptest.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.Locale;
import java.util.function.Function;

public final class QueryUtils {

    private QueryUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <E, X> Specification<E> isNull(Function<Root<E>, Expression<X>> function) {
        return (root, query, builder) -> builder.isNull(function.apply(root));
    }

    public static <E, X> Specification<E> isNotNull(Function<Root<E>, Expression<X>> function) {
        return (root, query, builder) -> builder.isNotNull(function.apply(root));
    }

    public static <E> Predicate equals(Function<Root<E>, Expression<?>> function, Object value, Root<E> root, CriteriaBuilder builder) {
        return builder.equal(function.apply(root), value);
    }

    public static <E, X> Specification<E> notEquals(Function<Root<E>, Expression<X>> function, X value) {
        return (root, query, builder) -> builder.notEqual(function.apply(root), value);
    }

    public static <E> Specification<E> is(Function<Root<E>, Expression<Boolean>> function,
                                          Boolean value) {
        return Boolean.TRUE.equals(value) ? isTrue(function) : isFalse(function);
    }

    public static <E> Specification<E> isTrue(Function<Root<E>, Expression<Boolean>> function) {
        return (root, query, builder) -> builder.isTrue(function.apply(root));
    }

    public static <E> Specification<E> isFalse(Function<Root<E>, Expression<Boolean>> function) {
        return (root, query, builder) -> builder.isFalse(function.apply(root));
    }

    public static <E, X extends Comparable<? super X>> Specification<E> gte(Function<Root<E>, Expression<X>> function, X value) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(function.apply(root), value);
    }


    public static <E, X extends Comparable<? super X>> Specification<E> gt(Function<Root<E>, Expression<X>> function, X value) {
        return (root, query, builder) -> builder.greaterThan(function.apply(root), value);
    }

    public static <E, X extends Comparable<? super X>> Specification<E> lte(Function<Root<E>, Expression<X>> function, X value) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(function.apply(root), value);
    }

    public static <E, X extends Comparable<? super X>> Specification<E> lt(Function<Root<E>, Expression<X>> function, X value) {
        return (root, query, builder) -> builder.lessThan(function.apply(root), value);
    }

    public static <E, X> Specification<E> in(Function<Root<E>, Expression<X>> function,
                                             Collection<X> values) {
        return (root, query, builder) -> {
            CriteriaBuilder.In<X> inPredicate = builder.in(function.apply(root));
            for (X value : values) {
                inPredicate = inPredicate.value(value);
            }
            return inPredicate;
        };
    }

    @SafeVarargs
    public static <E> Specification<E> search(String value,
                                              SingularAttribute<E, String>... expressions) {
        return (root, query, builder) -> {
            Predicate[] predicates = new Predicate[expressions.length];
            for (int i = 0; i < expressions.length; i++) {
                predicates[i] = builder.like(builder.lower(root.get(expressions[i])), wrapLikeQuery(value));
            }
            return builder.or(predicates);
        };
    }

    public static <E> Specification<E> like(Function<Root<E>, Expression<String>> function, String value) {
        return (root, query, builder) -> builder.like(builder.upper(function.apply(root)), wrapLikeQuery(value));
    }

    public static <E> Specification<E> notLike(Function<Root<E>, Expression<String>> function,
                                               String value) {
        return (root, query, builder) -> builder.notLike(builder.upper(function.apply(root)),
                wrapLikeQuery(value));
    }

    public static <E, X extends Comparable<? super X>> Specification<E> between(Function<Root<E>, Expression<X>> function, X start, X end) {
        return gte(function, start).and(lte(function, end));
    }

    private static String wrapLikeQuery(String txt) {
        return "%" + txt.toLowerCase(Locale.getDefault()) + "%";
    }
}
