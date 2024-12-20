package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;



public interface Playable {

    /**
     * @throws PlayerException 
     */
    void play() throws PlayerException;

    /**
    
     * @return 
     * @throws PlayerException 
     */
    String playMedia() throws PlayerException;
}
