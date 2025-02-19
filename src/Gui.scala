import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{BorderLayout, Dimension}
import geometry.Point
import javax.swing._
import main.{Generator, Renderer, Settings}

object Gui extends App with ActionListener
{
  val textArea = new JTextArea
  textArea.setText("Raytracer")

  val scrollPane = new JScrollPane(textArea)
  val frame = new JFrame("Raytracer")

  val panel = new JPanel()
  val bufferedImage = Converter.ppm(Settings.WIDTH, Settings.HEIGHT, Settings.NUMBER_OF_COLOURS, "image.ppm")
  val imageIcon = new ImageIcon(bufferedImage);
  //DECLARATIONS
  var label = new JLabel(imageIcon);
  val buttonSimulate = new JButton("Simulate");
  val cameraLabel = new JLabel()
  val cameraX = new JTextField(3);
  val cameraY = new JTextField(3);
  val cameraZ = new JTextField(3);
  val gammaLabel = new JLabel
  val gamma = new JTextField(3)
  val apertureLabel = new JLabel
  val aperture = new JTextField(3)
  val fieldOfViewLabel = new JLabel
  val fieldOfView = new JTextField(3)
  val maxDepthLabel = new JLabel
  val maxDepth = new JTextField(3)
  //COMPONENTS SETTINGS
  buttonSimulate.setSize(100, 100)
  buttonSimulate.addActionListener(this)
  cameraLabel.setText("Camera position (x, y, z): ")
  gammaLabel.setText("Gamma:")
  apertureLabel.setText("Aperture")
  fieldOfViewLabel.setText("Field of view [deg]")
  maxDepthLabel.setText("Max depth")
  //ADD COMPONENTS TO PANEL
  panel.add(buttonSimulate)
  panel.add(cameraLabel)
  panel.add(cameraX)
  panel.add(cameraY)
  panel.add(cameraZ)
  panel.add(gammaLabel)
  panel.add(gamma)
  panel.add(apertureLabel)
  panel.add(aperture)
  panel.add(fieldOfViewLabel)
  panel.add(fieldOfView)
  panel.add(maxDepthLabel)
  panel.add(maxDepth)
  panel.add(label)
  //FRAME SETTINGS
  frame.getContentPane.add(scrollPane, BorderLayout.CENTER)
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.setContentPane(panel)
  frame.setSize(new Dimension(Settings.WIDTH+100, Settings.HEIGHT+100))
  frame.setLocationRelativeTo(null)
  frame.setVisible(true)

  override def actionPerformed(e: ActionEvent): Unit = {
    //UPDATE SETTINGS
    Settings.GAMMA =  gamma.getText.toFloat
    Settings.MAX_DEPTH = maxDepth.getText.toInt
    val gen = new Generator("image.ppm");
    val lookFrom = new Point(cameraX.getText.toFloat,cameraY.getText.toFloat,cameraZ.getText.toFloat);
    val lookAt = new Point(0,0,-1);
    //GENERATE NEW IMAGE
    val matrix = Renderer.render(lookFrom, lookAt, aperture.getText.toFloat, fieldOfView.getText.toFloat)
    gen.loadScene(matrix)
    gen.save()
    //READ GENERATED IMAGE
    val bufferedImage = Converter.ppm(Settings.WIDTH, Settings.HEIGHT, Settings.NUMBER_OF_COLOURS, "image.ppm")
    val imageIcon = new ImageIcon(bufferedImage);
    //UPDATE image
    frame.remove(label)
    label = new JLabel(imageIcon);
    frame.add(label)
    frame.invalidate
    frame.validate
  }

}

