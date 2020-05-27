import java.awt.image.BufferedImage
import java.awt.{BorderLayout, Dimension}

import javax.swing.{JFrame, JScrollPane, JTextArea}
import main.Settings

object Gui extends App {

  import javax.swing.ImageIcon
  import javax.swing.JLabel





  val textArea = new JTextArea
  textArea.setText("Hello, Swing world")
  val scrollPane = new JScrollPane(textArea)

  val frame = new JFrame("Hello, Swing")
  frame.getContentPane.add(scrollPane, BorderLayout.CENTER)

  import javax.swing.WindowConstants

  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  val bufferedImage = Converter.ppm(Settings.WIDTH, Settings.HEIGHT, Settings.NUMBER_OF_COLOURS, "haha.ppm")
  val imageIcon = new ImageIcon(bufferedImage);
  val label = new JLabel(imageIcon);
  frame.add(label)

  frame.setSize(new Dimension(Settings.WIDTH, Settings.HEIGHT))
  frame.setLocationRelativeTo(null)
  frame.setVisible(true)

}

