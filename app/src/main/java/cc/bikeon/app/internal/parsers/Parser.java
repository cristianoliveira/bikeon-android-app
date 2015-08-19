package cc.bikeon.app.internal.parsers;

/**
 * Interface for Parse Classes
 * Created by cristianoliveira on 18/08/15.
 */
public interface Parser<To, From> {
    To parse(From from);
}
