package com.madden.anrjustcards.model.datumpredicates;

import com.google.common.base.Predicate;
import com.google.common.primitives.Ints;
import com.madden.anrjustcards.model.pojo.Datum;


public abstract class DatumPredicate implements Predicate<Datum> {
    protected String query;

    public void setQuery(String query) {
        this.query = query != null ? query.trim() : "";
    }

    protected int parseIntIfAble(String value) {
        Integer parsed = Ints.tryParse(value);
        return parsed == null ? -1 : parsed;
    }


    // datum predicates

    public static class Name extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.title.toLowerCase().contains(query);
        }
    }

    public static class Text extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.text != null && input.text.toLowerCase().contains(query);
        }
    }

    public static class Flavor extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.flavor != null && input.flavor.toLowerCase().contains(query);
        }
    }

    public static class Subtype extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.typeCode.toLowerCase().contains(query);
        }
    }

    public static class Cost extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.cost == parseIntIfAble(query);
        }
    }

    public static class AgendaPoints extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.agendaPoints == parseIntIfAble(query);
        }
    }

    public static class FactionCost extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.factionCost == parseIntIfAble(query);
        }
    }

    public static class Strength extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.strength == parseIntIfAble(query);
        }
    }

    public static class AdvancementCost extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.advancementCost == parseIntIfAble(query);
        }
    }

    public static class TrashCost extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.trashCost == parseIntIfAble(query);
        }
    }

    public static class Uniqueness extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.uniqueness && (query.equalsIgnoreCase("true") || query.equalsIgnoreCase("yes"));
        }
    }

    public static class Quantity extends DatumPredicate {
        @Override
        public boolean apply(Datum input) {
            return input.quantity == parseIntIfAble(query);
        }
    }
}