package net.take;

import org.limeprotocol.MediaType;

/***
 * Represent common media types
 */
public class MediaTypes
{
    /**
     * Represents any media type
     */
    public static MediaType getAny() { return new MediaType("*", "*"); };

    /***
     * Represents media type 'text/plain'
     */
    public static MediaType getPlainText() { return new MediaType(MediaType.DiscreteTypes.Text, MediaType.SubTypes.Plain); };
}
