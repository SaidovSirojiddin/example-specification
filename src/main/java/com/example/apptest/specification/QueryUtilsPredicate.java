package com.example.apptest.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public final class QueryUtilsPredicate {

    private QueryUtilsPredicate() {
        throw new IllegalStateException("Utility class");
    }

    public static <E, X> Predicate isNull(Function<Root<E>, Expression<X>> function,
                                          Root<E> root,
                                          CriteriaBuilder builder) {
        return builder.isNull(function.apply(root));
    }

    public static <E, X> Predicate isNotNull(Function<Root<E>, Expression<X>> function,
                                             Root<E> root,
                                             CriteriaBuilder builder) {
        return builder.isNotNull(function.apply(root));
    }

    public static <E, X> Predicate equals(Function<Root<E>, ? extends Expression<? extends X>> function,
                                          Root<E> root,
                                          CriteriaBuilder builder,
                                          X value) {
        return builder.equal(function.apply(root), value);
    }

    public static <E, X> Predicate notEquals(Function<Root<E>, ? extends Expression<? extends X>> function,
                                             Root<E> root,
                                             CriteriaBuilder builder,
                                             X value) {
        return builder.notEqual(function.apply(root), value);
    }

    public static <E> Predicate is(Function<Root<E>, Expression<Boolean>> function,
                                   Root<E> root,
                                   CriteriaBuilder builder,
                                   Boolean value) {
        return Boolean.TRUE.equals(value) ? isTrue(function, root, builder) : isFalse(function, root, builder);
    }

    public static <E> Predicate isTrue(Function<Root<E>, Expression<Boolean>> function,
                                       Root<E> root,
                                       CriteriaBuilder builder) {
        return builder.isTrue(function.apply(root));
    }

    public static <E> Predicate isFalse(Function<Root<E>, Expression<Boolean>> function,
                                        Root<E> root,
                                        CriteriaBuilder builder) {
        return builder.isFalse(function.apply(root));
    }

    public static <E, X extends Comparable<? super X>> Predicate gte(
            Function<Root<E>, Expression<X>> function,
            Root<E> root,
            CriteriaBuilder builder,
            X value) {
        return builder.greaterThanOrEqualTo(function.apply(root), value);
    }


    public static <E, X extends Comparable<? super X>> Predicate gt(Function<Root<E>, Expression<X>> function,
                                                                    Root<E> root,
                                                                    CriteriaBuilder builder,
                                                                    X value) {
        return builder.greaterThan(function.apply(root), value);
    }

    public static <E, X extends Comparable<? super X>> Predicate lte(Function<Root<E>, Expression<X>> function,
                                                                     Root<E> root,
                                                                     CriteriaBuilder builder,
                                                                     X value) {
        return builder.lessThanOrEqualTo(function.apply(root), value);
    }

    public static <E, X extends Comparable<? super X>> Predicate lt(Function<Root<E>, Expression<X>> function,
                                                                    Root<E> root,
                                                                    CriteriaBuilder builder,
                                                                    X value) {
        return builder.lessThan(function.apply(root), value);
    }

    public static <E, X> Predicate in(
            Function<Root<E>, Expression<X>> function,
            Root<E> root,
            CriteriaBuilder builder,
            Collection<X> values) {
        CriteriaBuilder.In<X> inPredicate = builder.in(function.apply(root));
        for (X value : values) {
            inPredicate = inPredicate.value(value);
        }
        return inPredicate;
    }

    @SafeVarargs
    public static <E> List<Predicate> search(Root<E> root,
                                             CriteriaBuilder builder,
                                             String value,
                                             SingularAttribute<E, String>... expressions) {

        List<Predicate> predicates = new ArrayList<>(expressions.length);
        for (int i = 0; i < expressions.length; i++)
            predicates.add(builder.like(builder.lower(root.get(expressions[i])), wrapLikeQuery(value)));

        return predicates;
    }

    public static <E> Predicate like(Function<Root<E>, Expression<String>> function,
                                     Root<E> root,
                                     CriteriaBuilder builder,
                                     String value) {
        return builder.like(builder.lower(function.apply(root)), wrapLikeQuery(value));
    }

    public static <E> Predicate notLike(Function<Root<E>, Expression<String>> function,
                                        Root<E> root,
                                        CriteriaBuilder builder,
                                        String value) {
        return builder.notLike(builder.lower(function.apply(root)),
                wrapLikeQuery(value));
    }

    public static <E, X extends Comparable<? super X>> Predicate between(Function<Root<E>, Expression<X>> function,
                                                                         Root<E> root,
                                                                         CriteriaBuilder builder,
                                                                         X start, X end) {
        return builder.between(function.apply(root), start, end);
    }

    private static String wrapLikeQuery(String txt) {
        return "%" + txt.toLowerCase(Locale.getDefault()) + "%";
    }

//    public static <E, X> Predicate equals(Function<Root<E>, ? extends Expression<? extends X>> function,
//                                          Root<E> root,
//                                          CriteriaBuilder builder,
//                                          X value) {
//        return builder.equal(function.apply(root), value);
//    }

//    public static <E> Predicate equals(Function<Root<E>, Expression<? extends UUID>> rootExpressionFunction, Root<E> genRoot, CriteriaBuilder builder, UUID value) {
//        return null;
//    }
}
