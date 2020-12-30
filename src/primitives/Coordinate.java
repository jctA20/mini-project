package primitives;

import primitives.Util;

/**
 * Class Coordinate is the basic class representing a coordinate for Cartesian
 * coordinate system. The class is based on Util controlling the accuracy.
 * 
 * @author Hodaya and Osnat
 */
public final class Coordinate {
    /**
     * Coordinate value, intentionally "package-friendly" due to performance
     * constraints
     */
    final double _coord;

    /**
     * Coordinate constructor receiving a coordinate value
     * 
     * @param coord coordinate value
     */
    public Coordinate(double coord) {
        // if it too close to zero make it zero
        _coord = Util.alignZero(coord);
    }

    /**
     * Copy constructor for coordinate
     * 
     * @param other coordinate
     */
    public Coordinate(Coordinate other) {
        _coord = other._coord;
    }

    /**
     * Coordinate value getter
     * 
     * @return coordinate value
     */
    public double get() {
        return _coord;
    }

	/*************** Admin *****************/
    /**
	 * @param obj - a coordinate object
	 * @return if two coordinate object are equals
	 */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;
        return Util.isZero(_coord - ((Coordinate)obj)._coord);
    }
    /**
	 * print the details of the coordinate
	 */
    @Override
    public String toString() {
        return "" + _coord;
    }
}
