package net.take;

import java.util.Collection;

public class EnumerableHelper
{
    public static Collection Coalesce(Collection collection, Collection alternative)
    {
        if (collection != null && !collection.isEmpty()) return collection;
        return alternative;
    }
}
