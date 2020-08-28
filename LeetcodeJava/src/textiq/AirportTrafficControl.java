package textiq;

// airport traffic control
// with a timeline, given tags (ACCEPTED, POSTPONED, LANDED)
// sort: small time -> small flight number
// landing time: 5 minutes
// postponed time: request after 10 minutes

import java.util.*;

class Flight {
    int num;
    int time;
    String tag;
    Flight (){};
    Flight (int num, int time) {
        this.num = num;
        this.time = time;
    }
    Flight (int num, int time, String tag) {
        this.num = num;
        this.time = time;
        this.tag = tag;
    }
}

public class AirportTrafficControl {

    private int freeRunways;

    private void checkAvailability(Queue<Flight> queue, List<Flight> rst, Queue<Flight> acceptedFlight, Queue<Flight> postponedFlight) {
        // put accepted flights into Queue<Flight> acceptedFlights
        // put postponed flights into Queue<Flight> postponedFlight
        int time = 0;
        while (( ! queue.isEmpty() || ! postponedFlight.isEmpty()) && freeRunways > 0) {
            Flight curt = new Flight();
            if ( ! queue.isEmpty()) curt = queue.poll();
            else if ( ! postponedFlight.isEmpty()) curt = postponedFlight.poll();
            curt.tag = "ACCEPTED";
            acceptedFlight.add(curt);
            // deep copy a new Flight (otherwise, if we modify Flight later, the Flight in rst will also be modified)
            rst.add(new Flight(curt.num, curt.time, "ACCEPTED"));
            freeRunways --;
            time = curt.time;
        }

        // curtFlight.time < last accepted flight.time + 5 -> postponed
        while (! queue.isEmpty() && time + 5 > queue.peek().time) {
            Flight curt = queue.poll();
            rst.add(new Flight(curt.num, curt.time, "POSTPONED"));
            // deep copy a new postponed flight with next request time
            postponedFlight.add(new Flight(curt.num, curt.time + 10));
        }
    }

    public List<Flight> trafficControl(int runways, List<Flight> flights) {

        List<Flight> rst = new ArrayList<>();
        // use PQ to save incoming flights
        Queue<Flight> queue = new PriorityQueue<>(
                (a, b) -> a.time == b.time ? a.num - b.num : a.time - b.time
        );

        for (Flight f : flights) queue.add(f);

        Queue<Flight> acceptedFlight = new LinkedList<>();
        Queue<Flight> postponedFlight = new PriorityQueue<>(
                (a, b) -> a.time == b.time ? a.num - b.num : a.time - b.time
        );

        freeRunways = runways;
        while ( ! queue.isEmpty() || ! postponedFlight.isEmpty()) {
            checkAvailability(queue, rst, acceptedFlight, postponedFlight);

            // land all accepted flights
            while ( ! acceptedFlight.isEmpty()) {
                Flight f = acceptedFlight.poll();
                freeRunways ++;
                rst.add(new Flight(f.num, f.time + 5, "LANDED"));
            }
        }
        return rst;
    }


    public static void main(String[] args) {
        List<Flight> flights = Arrays.asList(
                new Flight(367,45),
                new Flight(377,45),
                new Flight(357,50),
                new Flight(347,51)
        );

        int runways = 1;

        List<Flight> rst = new AirportTrafficControl().trafficControl(runways, flights);

        for (Flight f : rst) {
            String info = "Time: " + f.time + " Num: " + f.num + " Info: " + f.tag;
            System.out.println(info);
        }

    }
}
