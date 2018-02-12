<?php

  header("Cache-Control: no-cache, must-revalidate"); // HTTP/1.1
  header("Expires: Sat, 26 Jul 1997 05:00:00 GMT"); // Date in the past
  header("Content-type: text/html; charset=utf-8");
  header("Pragma: no-cache");

  function startExample($title = false) {
    global $index;
    echo "<div id=\"example-" . $index . "\" class=\"example\">\n";
    echo "<h3>Example " . $index;

    if($title) {
      echo ": " . $title;
    }

    echo "</h3>\n";
    echo "<div class=\"description\">\n";
  }

  function solution() {
    global $index;
    echo "</div><!-- description -->\n";
    echo "<div id=\"switch-" . $index . "\" class=\"switch\" " .
         " onclick=\"document.getElementById('solution-" . $index . "').style.display = ''; " .
         " document.getElementById('switch-" . $index . "').style.display = 'none';\">\n";
    echo "+ Show the solution!";
    echo "</div><!-- switch-" . $index . " -->\n";

    echo "<div id=\"solution-" . $index . "\" class=\"solution\" style=\"display: none;\">\n";
  }

  function endExample() {
    global $index;

    echo "</div><!-- solution-" . $index . " -->\n\n\n";

    echo "</div><!-- example-" . $index . " -->\n\n\n";
    $index++;
  }

  function startCode() {
    ob_start();
  }

  function endCode() {
    $output = ob_get_contents();
    ob_end_clean();
    echo "<div><div class=\"code\">\n" . str_replace(" ", "&nbsp;", str_replace("\n", "<br/>\n", htmlentities(trim($output)))) . "\n</div></div>\n";
  }

  global $index;
  $index = 1;

?><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>JAVA 8: Objects</title>
    <meta name="author" content="csega">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="description" content="Amateur education site for programming languages, like Java 8.">
    <meta name="keywords" content="programing languages, java, education">

    <style>
      body {
        background: #FCFCFC;
        color: #000000;
        font-family: Times New Roman;
        font-size: 12pt;
      }

      div.main {
        margin-bottom: 600px;
      }

      div.code {
        font-family: monospace;
        background: #F8F8F8;
        border: 1px #000000 dashed;
        padding: 5px;
        margin: 5px;
        display: inline-block;
        font-size: 10pt;
      }

      hr {
        margin-top: 5px;
        margin-bottom: 5px;
      }

      div.breadcrumbs {
        margin-bottom: 10px;
        font-family: Times New Roman;
        font-size: 12pt;
      } 

      div.breadcrumbs a {
        color: #202020;
        text-decoration: none;
      } 

      div.breadcrumbs a:hover {
        color: #808080;
        text-decoration: none;
      }

      div.example { 
        margin-top: 50px;
      }

      div.description { 
        color: #000000;
        background: #E0E0E0;
        width: 800px;
        margin-top: 10px;
        margin-bottom: 10px;
        padding: 5px;
        font-family: Times New Roman;
        font-size: 12pt;
      }

      div.switch { 
        color: #000000;
        background: #00B000;
        cursor: pointer;
        width: 800px;
        margin-top: 3px;
        margin-bottom: 3px;
        padding: 5px;
        font-family: Arial;
        font-size: 24px;
        font-weight: bold;
        font-style: italic;
      }

      div.switch:hover { 
        color: #606060;
        background: #60FF60;
      }

      div.solution { 
        color: #000000;
        background: #A0FFA0;
        width: 800px;
        margin-top: 10px;
        margin-bottom: 10px;
        padding: 5px;
        font-family: Times New Roman;
        font-size: 14pt;
      }

      div.solution div.code {
        background: #D0FFD0;
        border: 1px #000000 dotted;
      }
    </style>

  </head>
  <body>

    <div class="breadcrumbs">
      <a href="..">Main</a>
      &gt;
      <a href="./index.php">JAVA 8 basics</a>
      &gt;
      2. series: Objects
    </div>

    <hr/>

    <h1>Objects</h1>

    <div class="main">
<?php ob_start(); ?><script type="text/plain"><?php ob_end_clean(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class IntHolder {
  public int x = 5;
}

public class Example {
  public static void main(String[] args) {
    IntHolder ih = new IntHolder();
    for(int i = 0; i < 10; i++) {
      ih.x += 2;
    }
    System.out.println("x = " + ih.x);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
x = 25
<?php endCode(); ?>
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class IntHolder {
  public int x = 5;
}

public class Example {
  static void increase(IntHolder ih) {
    for(int i = 0; i < 10; i++) {
      ih.x += 2;
    }
  }

  public static void main(String[] args) {
    IntHolder ih = new IntHolder();
    increase(ih);
    System.out.println("x = " + ih.x);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
x = 25
<?php endCode(); ?>
When the argument of the method is a kind of object, the reference will be copied, but the object itself will be the same, so the value of <b>x</b> will be increased.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class IntHolder {
  public int x;

  public IntHolder(int x) {
    this.x = x;
  }
}

public class Example {
  static void increase(IntHolder ih) {
    for(int i = 0; i < 10; i++) {
      ih = new IntHolder(ih.x + 2);
    }
  }

  public static void main(String[] args) {
    IntHolder ih = new IntHolder(5);
    increase(ih);
    System.out.println("x = " + ih.x);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
x = 5
<?php endCode(); ?>
When the argument of the method is a kind of object, the reference will be copied, so when the variable <b>ih</b> gets a new value inside the method, it will not affect the IntHolder instance outside of the method.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
public class Example {
  static void increase(Integer x) {
    for(int i = 0; i < 10; i++) {
      x += 2;
    }
  }

  public static void main(String[] args) {
    Integer x = 5;
    increase(x);
    System.out.println("x = " + x);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
x = 5
<?php endCode(); ?>
When the <b>Integer</b> is passed as an argument, only the reference will be copied; but due to autoboxing the expression of
<?php startCode(); ?>
x += 2;
<?php endCode(); ?>
will not change the content of the (immutable) Integer object; rather, it will create a new instance, and will override the local <b>x</b> variable.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
public class Example {
  static void increase(int[] array) {
    for(int i = 0; i < 10; i++) {
      array[0] += 2;
    }
  }

  public static void main(String[] args) {
    int[] array = new int[] { 5 };
    increase(array);
    System.out.println("x = " + array[0]);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
x = 25
<?php endCode(); ?>
The array is an object, so its reference will be copied, but the array itself will be the same, and so changes in the elements will affect the original array variable as well.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
public class Example {
  static void increase(int[] array) {
    for(int i = 0; i < 10; i++) {
      array = new int[] { array[0] + 2 };
    }
  }

  public static void main(String[] args) {
    int[] array = new int[] { 5 };
    increase(array);
    System.out.println("x = " + array[0]);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
x = 5
<?php endCode(); ?>
The array is an objext, so its the reference will be copied, and when the variable <b>array</b> gets a new value inside the method, it will not affect the array outside of the method.
<?php endExample(); ?>

<?php ob_start(); ?></script><?php ob_end_clean(); ?>
      </div>

    <div><!-- main -->

  </body>
</html>
