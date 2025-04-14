package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class HasieraPantaila extends JFrame {
    private static HasieraPantaila nireHasieraPantaila = null;
    private JPanel contentPane;
    private static Integer bomber = null;
    private static String eszenario = null;

    private JButton bomber1Button;
    private JButton bomber2Button;
    private JButton classicBtn;
    private JButton softBtn;
    private JButton emptyBtn;
    private final ImageIcon bomber1Color = new ImageIcon(getClass().getResource("bomber1.png"));
    private final ImageIcon bomber1BW = new ImageIcon(getClass().getResource("bomber1BlackAndWhite.png"));
    private final ImageIcon bomber2Color = new ImageIcon(getClass().getResource("bomber2.png"));
    private final ImageIcon bomber2BW = new ImageIcon(getClass().getResource("bomber2BlackAndWhite.png"));

    private HasieraPantaila() {}
    
    public static synchronized HasieraPantaila getNireHasieraPantaila() {
        if (nireHasieraPantaila == null) {
            nireHasieraPantaila = new HasieraPantaila();
        }
        return nireHasieraPantaila;
    }

    public void initialize() {
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        contentPane.setBackground(Color.BLACK);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.BLACK);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(664, 402));

        ImageIcon background = new ImageIcon(getClass().getResource("back.png"));
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        ImageIcon title = new ImageIcon(getClass().getResource("title.png"));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(
            (layeredPane.getPreferredSize().width - title.getIconWidth()) / 2,
            20,
            title.getIconWidth(), 
            title.getIconHeight()
        );

        bomber1Button = botoiaBomberMan(1, bomber1BW, 80, 180);
        bomber2Button = botoiaBomberMan(2, bomber2BW, 150, 250);

        JPanel scenarioContainer = new JPanel();
        scenarioContainer.setOpaque(false);
        scenarioContainer.setLayout(new BoxLayout(scenarioContainer, BoxLayout.Y_AXIS));
        
        int rightMargin = 20;
        int bottomMargin = 20;
        
        JPanel topTextPanel = new JPanel();
        topTextPanel.setOpaque(false);
        topTextPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JLabel topLabel = new JLabel("LABYRINTH SETUP:");
        topLabel.setForeground(Color.BLACK);
        topLabel.setFont(new Font("Arial", Font.BOLD, 12));
        topTextPanel.add(topLabel);
        
        JPanel scenarioPanel = new JPanel();
        scenarioPanel.setOpaque(false);
        scenarioPanel.setLayout(new BoxLayout(scenarioPanel, BoxLayout.Y_AXIS));
        scenarioPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        Dimension buttonSize = new Dimension(135, 30);
        classicBtn = botoiaEszenarioa("CLASSIC", "EszenarioClassic");
        classicBtn.setBackground(Color.orange);
        softBtn = botoiaEszenarioa("SOFT", "EszenarioSoft");
        softBtn.setBackground(Color.orange);
        emptyBtn = botoiaEszenarioa("EMPTY", "EszenarioEmpty");
        emptyBtn.setBackground(Color.orange);
        
        classicBtn.setPreferredSize(buttonSize);
        classicBtn.setMaximumSize(buttonSize);
        softBtn.setPreferredSize(buttonSize);
        softBtn.setMaximumSize(buttonSize);
        emptyBtn.setPreferredSize(buttonSize);
        emptyBtn.setMaximumSize(buttonSize);
        
        scenarioPanel.add(classicBtn);
        scenarioPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        scenarioPanel.add(softBtn);
        scenarioPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        scenarioPanel.add(emptyBtn);
        
        JPanel bottomTextPanel = new JPanel();
        bottomTextPanel.setOpaque(false);
        bottomTextPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JLabel bottomLabel = new JLabel("<space> to start");
        bottomLabel.setForeground(Color.BLACK);
        bottomLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        bottomTextPanel.add(bottomLabel);
        
        scenarioContainer.add(topTextPanel);
        scenarioContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        scenarioContainer.add(scenarioPanel);
        scenarioContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        scenarioContainer.add(bottomTextPanel);
        
        scenarioContainer.setBounds(
            layeredPane.getPreferredSize().width - 150 - rightMargin,
            layeredPane.getPreferredSize().height - 150 - bottomMargin,
            150,
            150
        );

        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(titleLabel, Integer.valueOf(1));
        layeredPane.add(bomber1Button, Integer.valueOf(2));
        layeredPane.add(bomber2Button, Integer.valueOf(2));
        layeredPane.add(scenarioContainer, Integer.valueOf(2));

        centerPanel.add(layeredPane);
    }

    private JButton botoiaBomberMan(int bomberMota, ImageIcon iconoInicial, int x, int y) {
        JButton btn = new JButton(iconoInicial);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBounds(x, y, iconoInicial.getIconWidth(), iconoInicial.getIconHeight());

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bomber = bomberMota;
                if (bomberMota == 1) {
                    bomber1Button.setIcon(bomber1Color);
                    bomber2Button.setIcon(bomber2BW);
                } else if (bomberMota == 2) {
                    bomber1Button.setIcon(bomber1BW);
                    bomber2Button.setIcon(bomber2Color);
                }
                bomber1Button.setFocusable(false);
                bomber2Button.setFocusable(false);
                classicBtn.setFocusable(false);
                softBtn.setFocusable(false);
                emptyBtn.setFocusable(false);
            }
        });
        return btn;
    }


    private JButton botoiaEszenarioa(String mota, String eszenarioMota) {
        JButton btn = new JButton(mota);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eszenario = eszenarioMota;
                classicBtn.setBackground(Color.orange);
                softBtn.setBackground(Color.orange);
                emptyBtn.setBackground(Color.orange);
                btn.setBackground(Color.BLUE);
                classicBtn.setFocusable(false);
                softBtn.setFocusable(false);
                emptyBtn.setFocusable(false);
                bomber1Button.setFocusable(false);
                bomber2Button.setFocusable(false);
            }
        });
        return btn;
    }

	public static Integer getBomber() {return bomber;}

	public static String getEszenario() {return eszenario;}
}
