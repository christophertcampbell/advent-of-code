/**
 * Describes an orthagonal path (horizontal and vertical movements only). Provides methods
 * for finding intersections with another orthagonal path as well as calculating the nearest
 * intersection by Manhattan distance
 * 
 * A note about why I chose this solution:
 * I could have chosen a brute force method of simply creating an array of every single point
 * that the path traverses. But I wanted to solve this problem in an object-oriented way
 * more representative of how I develop for production environments, as well as have the
 * flexibility to add any necessary functionality to solve part B of the day's challenge
 * once it is revealed to me.
 * 
 * @param {int} startX The starting x-coordinate
 * @param {int} startY The starting y-coordinate
 * @param {string[]} directions An array of strings representing direction and distance travelled, in the following format:
 *        "R7": Move to the right seven spaces
 *        "L7": Move to the left seven spaces
 *        "U7": Move up seven spaces
 *        "D7": Move down seven spaces
 *        Example of a directions array: ["R7", "U4", "L14"]
 */
export function OrthagonalPath(startX, startY, directions) {

  let segments = new Segments();

  buildPath();

  /**
   * Builds a collection of segments which describes the path
   * in a way that facilitates analysis
   */
  function buildPath() {
    let previousY = startY;
    let previousX = startX;

    // Loop through each direction instruction, parsing and translating into a segment
    directions.forEach(function(input) {
      let direction = input.slice(0, 1).toLowerCase();
      let distance = Number(input.slice(1, input.length));

      let newX = previousX;
      let newY = previousY;

      switch(direction) {
        case "r":
          newX += distance;
          break;
        case "l":
          newX -= distance;
          break;
        case "u":
          newY += distance;
          break;
        case "d":
          newY -= distance;
          break;
      }

      segments.push(new Segment(new Point(previousX, previousY), new Point(newX, newY)));

      previousX = newX;
      previousY = newY;
    });
  }

  /**
   * Returns all segments of the path, in order
   * 
   * @returns {Segments} The collection of all segments
   */
  function getSegments() {
    return segments;
  }

  /**
   * Finds all intersections between this path and another
   * 
   * @param {OrthagonalPath} otherPath The other path to find intersections with
   * @returns {Point[]} An array of Points representing all intersections between the two paths
   */
  function getIntersections(otherPath) {
    let intersections = [];

    let horizontalSegments = segments.getAllHorizontal();
    let verticalSegments = segments.getAllVertical();

    let otherPathHorizontalSegments = otherPath.getSegments().getAllHorizontal();
    let otherPathVerticalSegments = otherPath.getSegments().getAllVertical();

    horizontalSegments.forEach(function(horizSegment) {
      otherPathVerticalSegments.forEach(function(verticalSegment) {
        let intersectionPoint = horizSegment.getIntersection(verticalSegment);
        if (intersectionPoint) {
          intersections.push(intersectionPoint);
        }
      })
    });
    
    verticalSegments.forEach(function(verticalSegment) {
      otherPathHorizontalSegments.forEach(function(horizSegment) {
        let intersectionPoint = horizSegment.getIntersection(verticalSegment);
        if (intersectionPoint) {
          intersections.push(intersectionPoint);
        }
      })
    })

    return intersections;
  }

  /**
   * Finds the intersection closest to a specified point between this path and another
   * 
   * Calculates the distance based on the Manhattan Distance principle (measuring
   * horizontal distance and vertical distance and adding the two, rather than
   * measuring directly between the two points)
   * 
   * @param {OrthagonalPath} otherPath The other path to find intersection with
   * @param {int} x The x-coordinate of the point to determine distance from
   * @param {int} y The y-coordinate of the point to determine distance from
   * @returns {Point} The closest point of intersection, or null if no intersection
   */
  function getClosestIntersection(otherPath, x, y) {
    let intersections = getIntersections(otherPath);
    let closestDist = -1;
    let closestPoint = null;

    intersections.forEach(function(point) {
      let xDist = Math.abs(point.x - x) || 0;
      let yDist = Math.abs(point.y - y) || 0;
      let totalDist = xDist + yDist;

      if (totalDist == 0 || closestDist > 0 && totalDist > closestDist) {
        return;
      }

      closestDist = totalDist;
      closestPoint = point;
    });

    return closestPoint;
  }

  /**
   * Finds the distance to the intersection closest to a specified point between this path and another
   * 
   * Calculates the distance based on the Manhattan Distance principle (measuring
   * horizontal distance and vertical distance and adding the two, rather than
   * measuring directly between the two points)
   * 
   * @param {OrthagonalPath} otherPath The other path to find intersection with
   * @param {int} x The x-coordinate of the point to determine distance from
   * @param {int} y The y-coordinate of the point to determine distance from
   * @returns {int} The distance to the closest intersection, or -1 if no intersection
   */
  function getDistanceToClosestIntersection(otherPath, x, y) {
    let closestIntersection = getClosestIntersection(otherPath, x, y);
    if (!closestIntersection) {
      return -1;
    }

    let xDist = Math.abs(closestIntersection.x - x) || 0;
    let yDist = Math.abs(closestIntersection.y - y) || 0;
    let totalDist = xDist + yDist;

    return totalDist > 0 ? totalDist : -1; 
  }

  return {
    getSegments: getSegments,
    getIntersections: getIntersections,
    getClosestIntersection: getClosestIntersection,
    getDistanceToClosestIntersection: getDistanceToClosestIntersection
  }
}

/**
 * A single two-dimensional point (x,y)
 * 
 * @param {int} x The x-coordinate of the point
 * @param {int} y The y-coordinate of the point
 */
function Point(x, y) {
  return {
    x: x,
    y: y
  }
}

/**
 * A line segment between two points
 * 
 * @param {Point} point1 The start point of the line segment
 * @param {Point} point2 The end point of the line segment
 */
function Segment(point1, point2) {

  function getIntersection(otherSegment) {

    // No intersection if both are horizontal or both are vertical
    if (isHorizontal() && otherSegment.isHorizontal() || isVertical() && otherSegment.isVertical()) {
      return null;
    }

    // Determine which segment is horizontal and which is vertical
    // so we can analyze them for intersection
    let horizSegment = isHorizontal() ? this : otherSegment;
    let verticalSegment = isVertical() ? this : otherSegment;

    // No intersection if the vertical line is left or right of the horizontal line
    if (verticalSegment.point1.x < horizSegment.point1.x && verticalSegment.point1.x < horizSegment.point2.x ||
        verticalSegment.point1.x > horizSegment.point1.x && verticalSegment.point1.x > horizSegment.point2.x) {
      return null
    }

    // No intersection if the horizontal line is above or below the vertical line
    if (horizSegment.point1.y < verticalSegment.point1.y && horizSegment.point1.y < verticalSegment.point2.y ||
        horizSegment.point1.y > verticalSegment.point1.y && horizSegment.point1.y > verticalSegment.point2.y) {
      return null
    }

    return new Point(verticalSegment.point1.x, horizSegment.point1.y);
  }

  function isVertical() {
    return point1.x == point2.x && point1.y != point2.y;
  }

  function isHorizontal() {
    return point1.y == point2.y && point1.x != point2.x;
  }

  return {
    point1: point1,
    point2: point2,
    getIntersection: getIntersection,
    isVertical: isVertical,
    isHorizontal: isHorizontal
  }
}

/**
 * A collection of line segments
 */
function Segments() {
  let segments = [];

  function push(segment) {
    segments.push(segment);
  }

  function getAllHorizontal() {
    return segments.filter(segment => segment.isHorizontal());
  }

  function getAllVertical() {
    return segments.filter(segment => segment.isVertical());
  }

  return {
    push: push,
    getAllHorizontal: getAllHorizontal,
    getAllVertical: getAllVertical
  }
}