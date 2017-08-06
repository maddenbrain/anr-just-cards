package com.madden.anrjustcards.model;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.madden.anrjustcards.model.datumpredicates.DatumPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DatumsSearchEngine {
    private static final String SPLIT_REGEX = "\\,|\\;";
    private static final String SPLIT_COMMAND = ":";

    private static Map<String, DatumPredicate> predicates = ImmutableMap.<String, DatumPredicate>builder()
            .put("t", new DatumPredicate.Name())
            .put("x", new DatumPredicate.Text())
            .put("a", new DatumPredicate.Flavor())
            .put("s", new DatumPredicate.Subtype())
            .put("o", new DatumPredicate.Cost())
            .put("v", new DatumPredicate.AgendaPoints())
            .put("n", new DatumPredicate.FactionCost())
            .put("p", new DatumPredicate.Strength())
            .put("g", new DatumPredicate.AdvancementCost())
            .put("h", new DatumPredicate.TrashCost())
            .put("u", new DatumPredicate.Uniqueness())
            .put("y", new DatumPredicate.Quantity())
            .build();


    public static Predicate generatePredicate(String query) {
        if (!query.contains(SPLIT_REGEX) && !query.contains(SPLIT_COMMAND)) {
            DatumPredicate predicate = new DatumPredicate.Name();
            predicate.setQuery(query);
            return predicate;
        }

        String[] commands = query.split(SPLIT_REGEX);
        List<Predicate> queryPredicates = new ArrayList<>();

        for (String command : commands) {
            String[] parts = command.split(SPLIT_COMMAND);
            if (parts.length < 2)
                continue;

            DatumPredicate predicate = predicates.get(parts[0].trim());
            if (predicate != null) {
                predicate.setQuery(parts[1]);
                queryPredicates.add(predicate);
            }
        }

        return Predicates.and(queryPredicates.toArray(new Predicate[0]));
    }
}