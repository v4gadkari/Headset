import components.map.Map;
import components.queue.Queue;
import components.set.Set;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 */

/**
 * @author Viren Gadkari
 *
 *         The main method for the Headset project Represents the main
 *         functionality when using the device
 *
 */
public class HeadsetTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        //two objects created, one to create the state of the headset itself
        //other object is to control interacting with picking the song from the
        //music library
        Headset hd = new Headset();
        Headset songPick = new Headset();

        //establish current state of headset

        hd.isOn = false; //is currently off

        hd.maxCharge = 100; //maximum battery limits at 100%

        hd.charge = 100; //current charge in the device is 100%

        hd.needCharge = false; //does not need charge

        hd.powerButton = false; //power is currently off and hasn't been pressed

        hd.isDead = false; //headset is not dead

        //main method boolean to determine when the user is done listening
        //user will want to listen when it turns on
        boolean doneListening = false;

        //prompt user to use device
        out.print("To use this device press [Y/y](only choices) : ");
        char choice = in.nextLine().charAt(0);
        out.println();

        //handles state of turning on the device
        //when the user chooses to use it
        // the headset will turn on and display current battery level
        hd.powerPressed(choice);

        if (hd.powerPressed(choice)) {

            hd.turnOn(out);
            out.println();
            hd.chargeLevels(out, in);
            out.println();

        }

        //enter the text file as data/Playlist contains the songs in library
        out.print("Enter the file: ");
        String inFile = in.nextLine();
        out.println();
        out.println();

        //reads in the textfile
        SimpleReader input = new SimpleReader1L(inFile);

        //map with original main library of songs (non-specific by artist)
        Map<String, String> customPlay = songPick.getMainPlaylist(input);

        //queue of all artists (non-specific)
        Queue<String> artists = songPick.artistNames(customPlay);

        //set of all artists (non-specific)
        Set<String> artistColl = songPick.currentArtists(artists);

        out.println("Your current music library: " + customPlay);
        out.println("Your library is very diverse!");
        out.println();
        out.println("Your current music library contains artists like: ");

        //outputs the diversity in the users playlist
        songPick.outputArtistDiversity(artistColl, out);

        //outer loop contains functionality for reprompting user for a new artist
        //as well as prompting for the number of seconds you wish to listen to the song
        //displays the charge level and only runs while the power is on and
        //the charge is greater than 0%

        //inner loop contains the functionality for the song library
        //contains prompts for picking the song, outputting the playlist
        //and queue for specific artist, as well as shuffling the queue
        //inner loop only runs while the user is still listening and the
        //charge is greater than zero
        while ((hd.charge > 0) && (hd.powerButton)) {

            //prompt for the artist:
            //(Travis Scott, Young Thug, Future, Playboi Carti or Kanye West)

            out.println();
            out.println(
                    "Would you like to listen to a specific artist playlist?: ");

            out.print("Enter the name of the artist: ");
            String artistName = in.nextLine();
            out.println();

            //specific artist playlist
            songPick.getDesiredPlaylist(artistName, customPlay, out);
            out.println();

            //specific artist queue
            Queue<String> songQueue = songPick.getDesiredQueue(artistName,
                    customPlay, out);
            out.println();

            //current song
            songPick.outputCurrSong(songQueue, out, artistName, in);
            out.println();

            //number of seconds you want to listen
            int songSeconds = songPick.promptForSongTime(out, in);

            while ((doneListening == false) && hd.charge > 0) {

                //output the current song playing
                songPick.outputCurrSong(songQueue, out, artistName, in);
                out.println();

                String currSong = songPick.getCurrSong(songQueue, artistName);

                //decrement charge by 2%
                hd.charge = hd.charge - 2;

                //decrement the seconds of the song
                songSeconds--;

                //output how many seconds left after each iteration
                out.println("You have " + songSeconds + " seconds remaining");

                //prompt the user to shuffle the queue when the song ends
                if (songSeconds == 0) {

                    //get the choice of the user
                    char shuffleChoice = songPick.promptForShuffle(out, in,
                            currSong, artistName);

                    //check the response, if yes, then the queue will shuffle
                    //will output the new current song
                    //reprompt the song time for the user
                    if (shuffleChoice == 'Y' || shuffleChoice == 'y') {

                        out.print("Enter the shuffle number: ");
                        int shuffleNo = in.nextInteger();

                        songPick.shufflePlaylist(songQueue, shuffleNo, out);
                        out.println();
                        songPick.outputCurrSong(songQueue, out, artistName, in);
                        out.println();
                        songSeconds = songPick.promptForSongTime(out, in);

                        //break out of loop if either of these scenarios occured
                        //temporary solutions but made loop work
                        if (hd.charge <= 0) {

                            break;
                        }

                        if (songSeconds < 0) {
                            break;
                        }

                    } else {

                        //if the user does not want to reshuffle, break the loop
                        //and push to outer loop
                        doneListening = true;

                        if (hd.charge <= 0) {

                            break;
                        }

                        if (songSeconds < 0) {
                            break;
                        }

                    }

                }

            }

            //display the current charge after and prompt the user to recharge
            //after before continuing the outer loop
            hd.chargeLevels(out, in);

            //if the user does not wish to charge, and the charge is zero
            //break out of the loop and end program

            if (hd.charge == 0) {

                break;

            }

            //at this point the user has decided to not shuffle for a new song
            //from the inner loop, and recharged their phone if they needed too
            //prompt the user if they want to listen to another artist
            out.print("Continue Listening?[Y/N] ");
            char artistChoice = in.nextLine().charAt(0);

            if (artistChoice == 'Y' || artistChoice == 'y') {

                //doneListening gets reset to false, to indicate that
                //the user is done listening to the artist within the inner
                //loop, but desire to continue using the device and pick
                //a different artist

                out.println("Great, go ahead and pick a new artist!");
                out.println();

                doneListening = false;

            } else {

                //if they do not want to continue listening
                //display the final amount of charge
                //end program, powers off
                out.println();
                out.println("Thanks for listening!");
                out.println("Your charge level was " + hd.charge + "%");
                break;
            }

        }

        //if it powers off, then the loop should break
        //should not ask to continue listening if power is dead

    }

}
