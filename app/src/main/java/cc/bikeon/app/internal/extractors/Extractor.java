package cc.bikeon.app.internal.extractors;

import java.util.Collection;

/**
 * Interface for extractors.
 * What do we meaning by extractor?
 * Receive a some collection and extract some data from that.
 *
 * Created by cristianoliveira on 22/08/15.
 */
public interface Extractor<Data, From extends Collection> {
    Data extract(From from);
}
