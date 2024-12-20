package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import javax.swing.*;

import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

public class MediaStore extends JPanel {
    private static final long serialVersionUID = 1L;
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title Label
        JLabel titleLabel = createTitleLabel(media.getTitle());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        // Cost Label
        JLabel costLabel = createCostLabel(media.getCost());
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(costLabel, gbc);

        // Play Button (if applicable)
        if (media instanceof Playable) {
            JButton playButton = createPlayButton();
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(playButton, gbc);
        }

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private JLabel createTitleLabel(String title) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 15));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setToolTipText("Title of the media");
        return titleLabel;
    }

    private JLabel createCostLabel(float cost) {
        JLabel costLabel = new JLabel(cost + " $");
        costLabel.setHorizontalAlignment(SwingConstants.CENTER);
        costLabel.setToolTipText("Cost of the media item");
        return costLabel;
    }

    private JButton createPlayButton() {
        JButton playButton = new JButton("Play");
        playButton.setAlignmentX(CENTER_ALIGNMENT);
        playButton.addActionListener(e -> {
            // Add play functionality here if the media is playable
            if (media instanceof Playable) {
                Playable playableMedia = (Playable) media;
                try {
                    playableMedia.play();
                } catch (PlayerException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }  // Assuming that 'play' is a method in Playable
            }
        });
        playButton.setToolTipText("Click to play this media");
        return playButton;
    }
}
