
import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplewriter.SimpleWriter;

/**
 *
 * Program which takes a textfile of songs, and simulates a playlist with user
 * interaction through a headset Allows the user to output a specific playlist
 * and song, shuffle a playlist to get a new song, and also select a new artist
 * if they wish For reality purposes, the user also is able to keep track of the
 * charge in the phone, and "charge" it when they want to.
 *
 * A sample textfile was created by me, and used as the input for the playlist
 *
 * Two objects are created, one for the playlist and picking songs, and another
 * for the actual headset itself
 *
 * @ Viren Gadkari
 *
 */
public class Headset {

    //class variables declared
    //one to control the state of the device being on or off
    //a powerbutton
    //the current charge in the phone
    //the maximum charge (100%)
    //when the user needs charge
    //when the device is dead

    static boolean isOn;
    static boolean powerButton;
    static int charge;
    static int maxCharge;
    static boolean needCharge;
    static boolean isDead;

    //no argument constructor
    public Headset() {

        this.isOn = isOn;
        this.powerButton = powerButton;
        this.charge = charge;
        this.needCharge = needCharge;
        this.maxCharge = maxCharge;
        this.isDead = isDead;

    }

    /**
     *
     * Models the input of a user to turn on the headset if the user wishes to
     * turn on the headset, then the button registers action
     *
     * @param char
     *            choice choice of the user to turn on headset
     *
     * @return boolean powerButton models the action of pressing powerbutton to
     *         turn on headset
     *
     */
    public static boolean powerPressed(char choice) {

        //powerbutton action is false when not turned on
        powerButton = false;

        //if user chooses to turn on power, button registers as true
        //action is desired
        if (choice == 'Y' || choice == 'y') {

            powerButton = true;

        }

        return powerButton;
    }

    /**
     *
     * Displays message to console when power is turned on
     *
     *
     * @param SimpleWriter
     *            out output stream to display message to the user
     *
     *
     */
    public static void turnOn(SimpleWriter out) {

        //headset state is off when power is not pressed
        isOn = false;

        //if user pushes powerbutton, the device will turn on
        if (powerButton == true) {

            isOn = true;
            out.println("Powering on...");

        }

    }

    /**
     *
     * Displays message to console when the power is turned off
     *
     * @param SimpleWriter
     *            out output stream to display message to the user
     *
     *
     *
     *
     */
    public static void turnOff(SimpleWriter out) {

        //headset is state is on when powerbutton has been pressed
        isOn = true;

        //if the user presses the button to turn off the device
        //or when the headset loses battery
        //turn off the device
        if (powerButton == false || isDead == true) {

            isOn = false;
            out.println("Powering off...");

        }

    }

    /**
     *
     * Prompts the user to charge the device
     *
     *
     * @param SimpleWriter
     *            out output stream to display prompt to user
     *
     * @param SimpleReader
     *            in input stream for user to answer whether they wish to charge
     *            device or not
     *
     *
     */
    public static void askUserCharge(SimpleWriter out, SimpleReader in) {

        //calculates the charge used by the user
        //by taking the difference between the
        //maximum charge level possible (100%) and current charge level
        int chargeUsed = maxCharge - charge;

        //prompt to user, take choice from user
        out.print("Would you like to charge your phone?[Y/N]: ");
        char choice = in.nextLine().charAt(0);

        //boolean variable declared when the state of device needs to be charged
        needCharge = false;

        //if else statement which runs based on user input
        if (choice == 'Y' || choice == 'y') {

            //if the user wishes to charge device, needCharge becomes true
            //and carries out procedure of charging the battery
            needCharge = true;

            if (needCharge == true) {

                //adds the difference calculated above to restore the power
                //back to 100% charge
                out.println("Your phone needs battery, plug in charger");
                out.println("Charging........");
                charge = charge + chargeUsed;
                out.println("You are now at " + charge + "%");

            }

        }

        else {

            //sometimes the charge as a decent level, so will continue to play
            //the song if the user does not wish to charge the device
            out.println("Okay, will continue playing song");
            out.println();

        }

    }

    /**
     *
     * Prompts the user to charge the headset when it is dead
     *
     *
     * @param SimpleWriter
     *            out output stream to display prompt to user
     *
     * @param SimpleReader
     *            in input stream to read input from the user if they wish to
     *            charge the phone
     *
     *
     */
    public static void chargeDeadHeadset(SimpleWriter out, SimpleReader in) {

        //makes the same calculation as before
        int chargeUsed = maxCharge - charge;

        out.println("Your phone is now dead");
        out.println("Please charge phone now or else it will power off");
        out.print("Charge phone now? [Y/N]: ");
        char choice = in.nextLine().charAt(0);

        out.println();

        //state of needing charge is false before, user can wish to leave phone
        //dead if they choose to, but at the cost of not being able to use the headset
        //unless they power the phone up

        needCharge = false;

        if (choice == 'Y' || choice == 'y') {

            needCharge = true;

            //charges phone back to full power
            if (needCharge = true) {

                out.println("Charging.....");
                charge = charge + chargeUsed;
                out.println("You are now at " + charge + "%");

            }

        } else {

            //uses the turnOff method to power off the phone when dead
            out.println("Your phone will now power off...");
            out.println("Charge to reboot....");
            turnOff(out);

        }

    }

    /**
     *
     * Displays the charge level of the device, and prompts the user to charge
     * when the device hits specific levels of charge
     *
     * @param SimpleWriter
     *            out
     *
     * @param SimpleReader
     *            in
     *
     *
     *
     *
     */
    public static void chargeLevels(SimpleWriter out, SimpleReader in) {

        //each level of charge displays the user what the current charge is
        //how much charge they have used, as well as how much charge is left
        //will prompt the user to charge the device at certain levels

        //same calculation
        int chargeUsed = maxCharge - charge;

        //state of device when the battery is dead is false
        isDead = false;

        //outputs the current charge level of the device
        out.println("Your current charge is " + charge + "%");

        if (charge == 100) {

            out.println(
                    "You have used " + chargeUsed + "%" + " of the battery");
            out.println("You currently have " + charge + "%" + " charge left");
        }

        if (charge == 70) {

            out.println(
                    "You have used " + chargeUsed + "%" + " of the battery");
            out.println("You currently have " + charge + "%" + " charge left");
            out.println("You still have more than enough!");

        }

        //at 50% prompt user to charge the device
        if (charge == 50) {

            out.println(
                    "You have used " + chargeUsed + "%" + " of the battery");
            out.println("You currently have " + charge + "%" + " charge left");
            out.println(
                    "You are halfway done, you still have enough battery life!");
            out.println();
            askUserCharge(out, in);

        }

        //at 20% prompt the user to charge the device
        if (charge == 20) {

            out.println(
                    "You have used " + chargeUsed + "%" + " of the battery");
            out.println("You currently have " + charge + "%" + " charge left");
            out.println("You may want to charge soon!");
            out.println();
            askUserCharge(out, in);

        }

        //at 10% prompt the user to charge the device
        if (charge == 10) {

            out.println(
                    "You have used " + chargeUsed + "%" + " of the battery");
            out.println("You currently have " + charge + "%" + " charge left");
            out.println("Charge immediately!");
            askUserCharge(out, in);

        }

        //when the charge is dead, indicate the state of the device being dead

        if (charge == 0) {

            //state of device is now dead when charge is zero
            isDead = true;

            out.println(
                    "You have used " + chargeUsed + "%" + " of the battery");
            out.println("You currently have " + charge + "%" + " charge left");

            out.println();

            //if the charge is dead, prompt to charge dead phone, otherwise
            //the user loses access, because the device powers off
            if (isDead == true) {

                chargeDeadHeadset(out, in);
            } else {
                turnOff(out);
            }

        }

    }

    /**
     *
     * Runs through textfile, and adds every song and artist to a map as a key
     * value pair. Since a map cannot hold more than one key with the same name
     * the song will be the kay, mapped to the value of the artist in order to
     * compensate for multiple songs by the same artist
     *
     *
     * @param input
     *            input stream to read the contents of the textfile in
     *
     * @return Map<String,String> playlist Map with each key (song) mapped to
     *         the value (artist)
     *
     *
     *
     *
     */

    public static Map<String, String> getMainPlaylist(SimpleReader input) {

        Map<String, String> playlist = new Map1L<String, String>();

        //initialized as empty strings to begin
        String song = "";
        String temp = "";

        //runs while the text file is not at end of stream
        while (!input.atEOS()) {

            song = input.nextLine();
            temp = input.nextLine();
            String artist = "";

            //temp string declared in case for an empty line
            //while the temp does not get an empty string
            //this value will be given to the artist
            while (!input.atEOS() && temp.length() != 0) {

                artist += temp;
                temp = input.nextLine();

            }

            //add song and artist to the map
            playlist.add(song, artist);

        }

        //this returns the full playlist of songs
        //think of this as the main library section of your music app
        return playlist;
    }

    /**
     *
     * Removes a random key value pair, and adds the artist name to a queue This
     * queue will be useful for later data passing and organization The queue
     * holds all the names of the artist in the playlist
     *
     * @param Map<String,String>
     *            playlist map which holds all of the key value pairs from
     *            textfile, and is restored after operation
     *
     * @return Queue<String> Queue of all the artists from the textfile
     *
     *
     *
     *
     */
    public static Queue<String> artistNames(Map<String, String> playList) {

        //instantiate an empty queue to hold the artists
        Queue<String> artists = new Queue1L<String>();

        //create a temporary map to hold values for iteration
        //this will help in restoring the original map
        Map<String, String> temp = playList.newInstance();
        temp.transferFrom(playList);

        while (temp.size() > 0) {

            //pair is removed, a string holds the value of the pair (artist)
            Map.Pair<String, String> pair = temp.removeAny();

            String artist = pair.value();

            //original playlist map is restored by adding the key and value pair
            //which was removed from temp back into orginal map
            playList.add(pair.key(), artist);

            //queue holds the value of the pair (artist)
            artists.enqueue(artist);

        }

        //returned queue of all artists
        return artists;

    }

    /**
     *
     * This method is used for simple passage of data for upcoming methods Adds
     * each artist to the a set, but checks first to make sure the set does not
     * already include one of the values in the queue. Set will hold the names
     * of each artist only once
     *
     * @param Queue<String>
     *            of all the artists in the playlist (holds duplicates)
     *
     * @return Set<String> a set of the names of all the artists (5), but only
     *         one name each
     *
     *
     *
     *
     */
    public static Set<String> currentArtists(Queue<String> artists) {

        //instantiate an empty set
        Set<String> artistsInLibrary = new Set1L<String>();

        //iterate over queue
        while (artists.length() > 0) {

            String nameArtists = artists.dequeue();

            //set a string to the dequeue'd value, and make sure the set does
            //not already contain the artists name, if it does, rotate the queue
            //for the next artist name
            if (artistsInLibrary.contains(nameArtists)) {

                artists.rotate(1);

            } else {

                //adds to set with only one name per artist (total 5 artists)
                artistsInLibrary.add(nameArtists);

            }

        }
        //returns set with all artists
        return artistsInLibrary;

    }

    /**
     *
     * This method iterates over the set from the previous method, and outputs
     * the names of each artist in the playlist. This method is used in the
     * final output when displaying the diversity of the playlist to the user.
     *
     * @param Set<String>
     *            artistCollection Set which holds the names of 5 artists
     *
     * @param out
     *            output stream which will display the names of each artist to
     *            console
     *
     */
    public static void outputArtistDiversity(Set<String> artistCollection,
            SimpleWriter out) {

        //for each to loop over the set of artists, size of the set is not
        //changed

        for (String name : artistCollection) {

            //outputs each artists name
            out.println(name);

        }

    }

    /**
     *
     * This method is used to do store a queue of the songs from the map
     * playlist The queue will resemble a queue of songs that can be shuffled by
     * the user However, the user will have access to specific artist playlist
     * queues So this queue serves the purpose as an input for filtering out
     * songs in the future.
     *
     * @param Map<String,String>
     *            playList holds the values of all the key(song) value(artist)
     *            pairs
     *
     * @return Queue<String> songs holds all the values of songs from the main
     *         map playlist
     *
     *         retores the original playlist Map
     *
     *
     *
     *
     */
    public static Queue<String> songNames(Map<String, String> playList) {

        //empty queue for songs
        Queue<String> songs = new Queue1L<String>();

        //create  a temporary map so that playList can be restored
        Map<String, String> temp = playList.newInstance();
        temp.transferFrom(playList);

        while (temp.size() > 0) {

            //remove a key value pair, instantiate a string for the song (key)
            Map.Pair<String, String> pair = temp.removeAny();

            String song = pair.key();

            //add the pair back into the original map
            playList.add(song, pair.value());

            //add the songs to a queue
            songs.enqueue(song);

        }

        return songs; //return the queueu with songs

    }

    //the following methods all breakup the main library playlist into playlist
    //for specific artists

    /**
     *
     * This method filters out the songs from the map playlist in order to
     * filter out and create a playlist for Travis Scott
     *
     *
     * @param String
     *            artist the name of the artist that is inputted by user, this
     *            will determine what playlist will be outputted
     *
     * @param Map<String,String>
     *            playList holds all the key value pairs for the orginal song
     *            library
     *
     * @return Map<String,String> travPlaylist a similar map as the original
     *         playlist, except with only songs by Travis Scott
     *
     *
     */
    public static Map<String, String> travPlaylist(String artist,
            Map<String, String> playList) {

        //instantiated playlist for artist as a map
        Map<String, String> travisPlaylist = new Map1L<String, String>();

        //create a temp of the original map playlist for easy restoration
        Map<String, String> temp = playList.newInstance();
        temp.transferFrom(playList);

        while (temp.size() > 0) {

            //remove a pair
            Map.Pair<String, String> pair = temp.removeAny();

            //set desired artist equal to the value of the pair
            artist = pair.value();

            //if the artist name starts with a T (Travis Scott)
            if (artist.charAt(0) == 'T' || artist.charAt(0) == 't') {

                //add the key value pair to the travisPlaylist map
                //this allows for filtering of travis scott songs only
                travisPlaylist.add(pair.key(), artist);

            }

            //restore the original playlist
            playList.add(pair.key(), pair.value());

        }

        //return the map with only travis scott songs
        return travisPlaylist;

    }

    /**
     *
     * This method filters out the songs from the map playlist in order to
     * filter out and create a playlist for Young Thug
     *
     *
     * @param String
     *            artist the name of the artist that is inputted by user, this
     *            will determine what playlist will be outputted
     *
     * @param Map<String,String>
     *            playList holds all the key value pairs for the orginal song
     *            library
     *
     * @return Map<String,String> thugPlaylist a similar map as the original
     *         playlist, except with only songs by Young Thug
     *
     *
     */
    public static Map<String, String> thugPlaylist(String artist,
            Map<String, String> playList) {

        //instantiated playlist for artist as a map
        Map<String, String> thugPlaylist = new Map1L<String, String>();

        //create a temp of the original map playlist for easy restoration
        Map<String, String> temp = playList.newInstance();
        temp.transferFrom(playList);

        while (temp.size() > 0) {

            //remove a pair
            Map.Pair<String, String> pair = temp.removeAny();

            //set desired artist equal to the value of the pair
            artist = pair.value();

            //if the artist name starts with a Y (Young Thug)
            if (artist.charAt(0) == 'Y' || artist.charAt(0) == 'y') {

                //add the key value pair to the thugPlaylist map
                //this allows for filtering of Young Thug songs only
                thugPlaylist.add(pair.key(), artist);
            }

            //restore the original playlist
            playList.add(pair.key(), pair.value());
        }

        //return the map with only young thug songs
        return thugPlaylist;

    }

    /**
     *
     * This method filters out the songs from the map playlist in order to
     * filter out and create a playlist for Kanye West
     *
     *
     * @param String
     *            artist the name of the artist that is inputted by user, this
     *            will determine what playlist will be outputted
     *
     * @param Map<String,String>
     *            playList holds all the key value pairs for the orginal song
     *            library
     *
     * @return Map<String,String> kanyePlaylist a similar map as the original
     *         playlist, except with only songs by Kanye West
     *
     *
     */
    public static Map<String, String> kanyePlaylist(String artist,
            Map<String, String> playList) {

        //instantiated playlist for artist as a map
        Map<String, String> kanyePlaylist = new Map1L<String, String>();

        //create a temp of the original map playlist for easy restoration
        Map<String, String> temp = playList.newInstance();
        temp.transferFrom(playList);

        while (temp.size() > 0) {

            //remove a pair
            Map.Pair<String, String> pair = temp.removeAny();

            //set desired artist equal to the value of the pair
            artist = pair.value();

            //if the artist name starts with a K (Kanye West)
            if (artist.charAt(0) == 'K' || artist.charAt(0) == 'k') {

                kanyePlaylist.add(pair.key(), artist);
            }

            //restore the original playlist
            playList.add(pair.key(), pair.value());
        }

        //return the map with only kanye west songs
        return kanyePlaylist;

    }

    /**
     *
     * This method filters out the songs from the map playlist in order to
     * filter out and create a playlist for Future
     *
     *
     * @param String
     *            artist the name of the artist that is inputted by user, this
     *            will determine what playlist will be outputted
     *
     * @param Map<String,String>
     *            playList holds all the key value pairs for the orginal song
     *            library
     *
     * @return Map<String,String> futurePlaylist a similar map as the original
     *         playlist, except with only songs by Future
     *
     *
     */
    public static Map<String, String> futurePlaylist(String artist,
            Map<String, String> playList) {

        //instantiated playlist for artist as a map
        Map<String, String> futurePlaylist = new Map1L<String, String>();

        //create a temp of the original map playlist for easy restoration
        Map<String, String> temp = playList.newInstance();
        temp.transferFrom(playList);

        while (temp.size() > 0) {

            //remove a pair
            Map.Pair<String, String> pair = temp.removeAny();

            //set desired artist equal to the value of the pair
            artist = pair.value();

            if (artist.charAt(0) == 'F' || artist.charAt(0) == 'f') {

                //if the artist name starts with a F (Future)
                futurePlaylist.add(pair.key(), artist);
            }

            //restore the original playlist
            playList.add(pair.key(), pair.value());
        }

        //return the map with only future songs
        return futurePlaylist;
    }

    /**
     *
     * This method filters out the songs from the map playlist in order to
     * filter out and create a playlist for Playboi Carti
     *
     *
     * @param String
     *            artist the name of the artist that is inputted by user, this
     *            will determine what playlist will be outputted
     *
     * @param Map<String,String>
     *            playList holds all the key value pairs for the orginal song
     *            library
     *
     * @return Map<String,String> cartiPlaylist a similar map as the original
     *         playlist, except with only songs by Playboi Carti
     *
     *
     */
    public static Map<String, String> cartiPlaylist(String artist,
            Map<String, String> playList) {

        //instantiated playlist for artist as a map
        Map<String, String> cartiPlaylist = new Map1L<String, String>();

        //create a temp of the original map playlist for easy restoration
        Map<String, String> temp = playList.newInstance();
        temp.transferFrom(playList);

        while (temp.size() > 0) {

            //remove a pair
            Map.Pair<String, String> pair = temp.removeAny();

            //set desired artist equal to the value of the pair
            artist = pair.value();

            //if the artist name starts with a P (Playboi Carti)
            if (artist.charAt(0) == 'P' || artist.charAt(0) == 'p') {

                cartiPlaylist.add(pair.key(), artist);
            }

            //restore the original playlist
            playList.add(pair.key(), pair.value());
        }

        //return the map with only Playboi Carti songs
        return cartiPlaylist;

    }

    /**
     *
     * Displays the queue of songs based on what playlist is desired by the user
     * Displays the queue of songs specific to the artistPlaylist generated
     * above
     *
     * @param Map<String,String>
     *            artistPlaylist playList holds all the key value pairs for the
     *            specific artist playlist that is desired by the user
     *
     *
     * @return Queue<String> specQueue queue which holds the songs for the
     *         specific artist, represents the real world queue that would hold
     *         songs for a playlist in an app such as spotify. This song queue
     *         can be shuffled to output a different song each time
     *
     *
     *
     *
     *
     *
     *
     */
    public static Queue<String> currentSongQueue(
            Map<String, String> artistPlaylist) {

        //create a queue for the songs specific top artist
        Queue<String> specQueue = new Queue1L<String>();

        //create a temp map for restoration purposes
        Map<String, String> temp = artistPlaylist.newInstance();
        temp.transferFrom(artistPlaylist);

        while (temp.size() > 0) {

            //remove a random pair from the specific playlist
            Map.Pair<String, String> pair = temp.removeAny();

            //set the name of the artist equal to the value of the pair
            String artist = pair.value();

            //the following cases check to see who the specific artist is
            //based on what the input is, the queue will hold the specific
            //key(song) that matches the artist, filters out each queue
            //based on the artist that is removed

            //creating a travis scott queue if user wished for Travis Scott playlist
            if (artist.charAt(0) == 'T' || artist.charAt(0) == 't') {

                //setting the song name to the key
                String travSong = pair.key();

                //add to the travis scott queue
                specQueue.enqueue(travSong);

            }

            //creating a young thug queue if the user wishes for a Young Thug playlist
            if (artist.charAt(0) == 'Y' || artist.charAt(0) == 'y') {

                //song name
                String thuggerSong = pair.key();

                //adding to the specific young thug queue
                specQueue.enqueue(thuggerSong);

            }

            //creating queue if the user wishes for a future playlist
            if (artist.charAt(0) == 'F' || artist.charAt(0) == 'f') {

                //song name
                String futureSong = pair.key();

                //adding to the specific future queue
                specQueue.enqueue(futureSong);

            }

            //creating queue if the user wishes for a kanye west playlist
            if (artist.charAt(0) == 'K' || artist.charAt(0) == 'k') {

                //song name
                String kanyeSong = pair.key();

                //adding to specific kanye west queue
                specQueue.enqueue(kanyeSong);

            }

            //creating queue if the user wishes for a playboi carti playlist
            if (artist.charAt(0) == 'P' || artist.charAt(0) == 'p') {

                //song name
                String cartiSong = pair.key();

                //adding to the specific playboi carti queue
                specQueue.enqueue(cartiSong);

            }

            //restoring original playlist for the specific artist
            artistPlaylist.add(pair.key(), pair.value());

        }

        //return the desired queue for the specific artist
        return specQueue;

    }

    /**
     *
     * Displays the playlist of songs as an unordered Set for the specific
     * artist
     *
     * @param Map<String,String>
     *            artistPlaylist The map for the specific artist that was
     *            desired by the user Holds the key value pairs of songs for the
     *            user inputted artist
     *
     * @return Set<String> specPlaylist The set of songs for the specific artist
     *         playlist, holds the songs to show the user what songs are
     *         available in the playlist. Since order does not matter it will
     *         only serve the purpose as visualizing what songs there are, and
     *         will not be removing any songs
     *
     *
     *
     *
     *
     */
    public static Set<String> specificPlaylist(
            Map<String, String> artistPlaylist) {

        //same algorithm as the specific queue method

        //playlist for the specific artist
        Set<String> specPlaylist = new Set1L<String>();

        //temporary map created for restoration purposes
        Map<String, String> temp = artistPlaylist.newInstance();
        temp.transferFrom(artistPlaylist);

        while (temp.size() > 0) {

            //removes a random pair from the specific artist map
            Map.Pair<String, String> pair = temp.removeAny();

            //stores artist as the value, to check for user input
            String artist = pair.value();

            //if the user wishes for travis scott songs
            if (artist.charAt(0) == 'T' || artist.charAt(0) == 't') {

                //similar algorithm as previous method

                String travSong = pair.key();
                specPlaylist.add(travSong);

            }

            //if the user wishes for young thug songs
            if (artist.charAt(0) == 'Y' || artist.charAt(0) == 'y') {

                String thuggerSong = pair.key();
                specPlaylist.add(thuggerSong);

            }

            //if the user wishes for future songs
            if (artist.charAt(0) == 'F' || artist.charAt(0) == 'f') {

                String futureSong = pair.key();
                specPlaylist.add(futureSong);

            }

            //if the user wishes for kanye songs
            if (artist.charAt(0) == 'K' || artist.charAt(0) == 'k') {
                String kanyeSong = pair.key();
                specPlaylist.add(kanyeSong);

            }

            //if the user wishes for playboi carti songs
            if (artist.charAt(0) == 'P' || artist.charAt(0) == 'p') {
                String cartiSong = pair.key();
                specPlaylist.add(cartiSong);

            }

            //restore original artist playlist map
            artistPlaylist.add(pair.key(), pair.value());

        }

        //return the desired playlist set
        return specPlaylist;
    }

    /**
     *
     * Prompts the user to charge the headset when it is dead
     *
     *
     * @param SimpleWriter
     *            out output stream to display prompt to user
     *
     * @param SimpleReader
     *            in input stream to read input from the user if they wish to
     *            charge the phone
     *
     *
     */

    /**
     *
     * This method outputs the desired set of songs that the user has asked for
     * for each case, the specific playlist is generated, along with the set of
     * songs
     *
     *
     * @param String
     *            User input telling device what artist they would like to
     *            listen to, the appropriate playlist and set of songs is
     *            generated from this
     *
     * @param Map<String,String>
     *            specific artist playlist the user desires, stored as a map of
     *            key value pairs specific to the artist
     *
     * @param SimpleWriter
     *            output stream to display the set of songs specific for the
     *            artist
     *
     *
     *
     *
     *
     */
    public static void getDesiredPlaylist(String artist,
            Map<String, String> artistPlaylist, SimpleWriter out) {

        //if user inputs Travis Scott
        if (artist.charAt(0) == 'T' || artist.charAt(0) == 't') {

            //generate the map of travis scott songs (refer methods if need be)
            Map<String, String> cactusJack = travPlaylist(artist,
                    artistPlaylist);

            //generate the specific set of travis scott songs
            Set<String> cactusJackSongsPlaylist = specificPlaylist(cactusJack);

            //out put set of songs to user
            out.println("Your playlist for " + artist + ": "
                    + cactusJackSongsPlaylist);

        }

        //if user inputs Young Thug
        if (artist.charAt(0) == 'Y' || artist.charAt(0) == 'y') {

            //generate map of young thug songs  (refer methods if need be)
            Map<String, String> slime = thugPlaylist(artist, artistPlaylist);

            //generate the specific set of young thug songs
            Set<String> slimeSongsPlaylist = specificPlaylist(slime);

            //output set of songs to user
            out.println(
                    "Your playlist for " + artist + ": " + slimeSongsPlaylist);

        }

        //if user inputs future
        if (artist.charAt(0) == 'F' || artist.charAt(0) == 'f') {

            //generate map of future songs (refer to methods if need be)
            Map<String, String> pluto = futurePlaylist(artist, artistPlaylist);

            //generate the specific set of future songs
            Set<String> plutoSongsPlaylist = specificPlaylist(pluto);

            //output set of songs to user
            out.println(
                    "Your playlist for " + artist + ": " + plutoSongsPlaylist);

        }

        //if user inputs Kanye West
        if (artist.charAt(0) == 'K' || artist.charAt(0) == 'k') {

            //generate map of kanye songs (refer to methods if need be)
            Map<String, String> yeezy = kanyePlaylist(artist, artistPlaylist);

            //generate the specific set of kanye songs
            Set<String> yeezySongsPlaylist = specificPlaylist(yeezy);

            //output set of songs to user
            out.println(
                    "Your playlist for " + artist + ": " + yeezySongsPlaylist);

        }

        //if user inputs Playboi Carti
        if (artist.charAt(0) == 'P' || artist.charAt(0) == 'p') {

            //generate map of playboi carti songs (refer to methods if need be)
            Map<String, String> carti = cartiPlaylist(artist, artistPlaylist);

            //generate the specific set of playboi carti songs
            Set<String> cartiSongsPlaylist = specificPlaylist(carti);

            //output set to console
            out.println(
                    "Your playlist for " + artist + ": " + cartiSongsPlaylist);

        }

    }

    /**
     *
     * Returns the desired song queue for the specific artist, each case
     * represents a different artist that is requested by the user
     *
     * @param String
     *            the artist that the user inputs
     *
     *
     * @param Map<String,String>
     *            specific artist playlist the user desires, stored as a map of
     *            key value pairs specific to the artist
     *
     * @param out
     *            output stream to display the desired song queue to the user
     *
     *
     * @return Queue<String> the specific song queue that is desired for the
     *         specific artist, this will be used for further functionalities
     *         given to the user, such as shuffling the queue
     *
     *
     *
     */
    public static Queue<String> getDesiredQueue(String artist,
            Map<String, String> artistPlaylist, SimpleWriter out) {

        //instantiate an empty queue
        //will hold the songs for the specific queue in each case
        Queue<String> desiredSongsQueue = new Queue1L<String>();

        if (artist.charAt(0) == 'T' || artist.charAt(0) == 't') {

            //the desired specific playlist map for travis scott songs
            Map<String, String> cactusJack = travPlaylist(artist,
                    artistPlaylist);

            //instantiate a temporary song queue for travis scott
            Queue<String> cactusJackSongsQueue = currentSongQueue(cactusJack);

            //transfer into the original desiredSongs queue
            //this queue will be returned if the user wants travis scott songs
            desiredSongsQueue.transferFrom(cactusJackSongsQueue);
            out.println("Your current queue for " + artist + " playlist" + ": "
                    + desiredSongsQueue);

        }

        if (artist.charAt(0) == 'Y' || artist.charAt(0) == 'y') {

            //desired specific playlist map for young thug songs
            Map<String, String> slime = thugPlaylist(artist, artistPlaylist);

            //instantiate a temporary song queue for young thug
            Queue<String> slimeSongsQueue = currentSongQueue(slime);

            //transfer into the orginal desiredSongs queue
            //this queue will be returned if the user wants young thug songs
            desiredSongsQueue.transferFrom(slimeSongsQueue);
            out.println("Your current queue for " + artist + " playlist" + ": "
                    + desiredSongsQueue);

        }

        if (artist.charAt(0) == 'F' || artist.charAt(0) == 'f') {

            //desired specific playlist map for future songs
            Map<String, String> pluto = futurePlaylist(artist, artistPlaylist);

            //instantiate a temporary song queue for future
            Queue<String> plutoSongsQueue = currentSongQueue(pluto);

            //transfer into the original desiredSongs queue
            //this queue will be returned if the user wants future songs
            desiredSongsQueue.transferFrom(plutoSongsQueue);
            out.println("Your current queue for " + artist + " playlist" + ": "
                    + desiredSongsQueue);

        }

        if (artist.charAt(0) == 'K' || artist.charAt(0) == 'k') {

            //desired specific playlist map for kanye songs
            Map<String, String> yeezy = kanyePlaylist(artist, artistPlaylist);

            //instantiate a temporary song queue for kanye west
            Queue<String> yeezySongsQueue = currentSongQueue(yeezy);

            //transfer into the original desiredSongs queue
            //this queue will be returned if the user wants kanye west songs
            desiredSongsQueue.transferFrom(yeezySongsQueue);
            out.println("Your current queue for " + artist + " playlist" + ": "
                    + desiredSongsQueue);

        }

        if (artist.charAt(0) == 'P' || artist.charAt(0) == 'p') {

            //desired specific playlist map for playboi carti songs
            Map<String, String> carti = cartiPlaylist(artist, artistPlaylist);

            //instantiate a temporary song queue for playboi carti songs
            Queue<String> cartiSongsQueue = currentSongQueue(carti);

            //transfer into the original desiredSongs queue
            //this queue will be returned if the user wants playboi carti songs
            desiredSongsQueue.transferFrom(cartiSongsQueue);
            out.println("Your current queue for " + artist + " playlist" + ": "
                    + desiredSongsQueue);

        }

        //returned the desiredSongs queue based on the specific artist
        return desiredSongsQueue;

    }

    /**
     *
     * Displays the current song playing to the user, and displays the current
     * charge level
     *
     *
     * @param Queue<String>
     *            the current song queue displayed for the specific artist
     *
     * @param out
     *            the output stream that displays the current song and charge
     *            level to the user
     *
     * @param in
     *            the input stream which reads input for the chargeLevels method
     *
     * @param artist
     *            displays the artist that is requested by the users
     *
     */
    public static void outputCurrSong(Queue<String> songQ, SimpleWriter out,
            String artist, SimpleReader in) {

        //takes the front of the song queue and stores it as a string for output
        String currSong = songQ.front();

        //outputs the current song and artist
        out.println("You are currently listening to: " + currSong + " by "
                + artist);

        //displays level of charge, and necessary prompts
        chargeLevels(out, in);

    }

    /**
     *
     * Gets the current song as a string, used as a parameter for upcoming
     * methods
     *
     * @param Queue<String>
     *            current song queue for the specific artist
     *
     *
     * @param artist
     *            the artist desired by the user
     *
     *
     * @return currSong return the current song playing (front of queue) as a
     *         string
     *
     */
    public static String getCurrSong(Queue<String> songQ, String artist) {

        //store the front of the queue as the current song
        String currSong = songQ.front();

        return currSong;
    }

    /**
     *
     * Shuffles the queue to output a different song, rotates the order of
     * queue, and outputs the new queue
     *
     *
     * @param Queue<String>
     *            outputs the song queue for the specific artist
     *
     * @param shuffleNo.
     *            integer which represents by how many songs they want to
     *            shuffle the queue
     *
     *
     * @param out
     *            outputs the updated queue for the user
     *
     */
    public static void shufflePlaylist(Queue<String> songQ, int shuffleNo,
            SimpleWriter out) {

        //rotate the queue by the number of songs
        songQ.rotate(shuffleNo);

        //output to user
        out.println("Your new queue after shuffle: " + songQ);

    }

    /**
     *
     * Prompts the user if they want to shuffle the queue, returns the choice as
     * a character value for further parameter passing, while also showing the
     * song they had just listened to and by which artist
     *
     *
     * @param out
     *            output stream which prompts the question to the user
     *
     * @param in
     *            input stream which reads in the decision by the user
     *
     * @param currSong
     *            current song which is playing
     *
     * @param artist
     *            the artist for the current song playing
     *
     *
     * @return choice the choice that the user inputs for if they want to
     *         shuffle the queue
     *
     *
     */
    public static char promptForShuffle(SimpleWriter out, SimpleReader in,
            String currSong, String artist) {

        //display the song the user just listened to
        out.println("You just listened to " + currSong + " by " + artist);
        out.println();
        //prompt for if the user desires to shuffle the playlist
        out.print(
                "Would you like to listen shuffle playlist for a new song? [Y/N]: ");
        char choice = in.nextLine().charAt(0);

        //return the choice
        return choice;

    }

    /**
     *
     * Prompts the user for how long they would like to listen to their desired
     * song this is done to control how long a song will be played, and to model
     * when a song would be finished, returns the number seconds as an integer
     *
     *
     * @param out
     *            output stream which displays the question
     *
     *
     * @param in
     *            input stream which reads in the user desired seconds for song
     *
     *
     *
     * @return seconds
     * @requires 60<seconds<180 (cannot exceed 3 minutes or below 1 minute)
     *
     *           amount of time the user desires to listen to song
     *
     */
    public static int promptForSongTime(SimpleWriter out, SimpleReader in) {

        //instantiated boolean to keep track of prompting the user
        boolean stillPrompting = true;

        //prompt the for the number of seconds
        out.println("You have picked the song of your choice!");
        out.println("How long will you be listening to this song for?");
        out.print("3 minutes?, 2 minutes?, or 1 minute? (in Seconds): ");
        int seconds = in.nextInteger();
        out.println();

        //while the user is being prompted
        while (stillPrompting) {

            //if the number of seconds is out of range, remprompt user
            //for appropriate amount of seconds
            if (seconds < 60 || seconds > 180) {
                out.println();
                out.println("Sorry! incorrect input! Length out of range!");
                out.println("You must enter a time in seconds");
                out.println("How long will you be listening to this song for?");
                out.print(
                        "Either 3 minutes, 2 minutes, or 1 minutes (in Seconds): ");
                seconds = in.nextInteger();

            } else {
                //otherwise output the appropriate song time and end loop
                out.println();
                out.println("Your desired length of time is " + seconds
                        + " seconds");
                //since the seconds are in range, the loop can break and stop
                //prompting user
                stillPrompting = false;
            }

        }
        //return the appropriate number of seconds
        return seconds;
    }

}
