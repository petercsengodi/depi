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
    <title>JAVA 8: Primitives</title>
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
      1. series: Primitives
    </div>

    <hr/>

    <h1>Primitives</h1>

    <div class="main">
<?php ob_start(); ?><script type="text/plain"><?php ob_end_clean(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    int x = 5;
    for(int i = 0; i < 10; i++) {
      x += 2;
    }
    System.out.println("x = " + x);
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
class Example {
  static void increase(int x) {
    for(int i = 0; i < 10; i++) {
      x += 2;
    }
  }

  public static void main(String[] args) {
    int x = 5;
    increase(x);
    System.out.println("x = " + x);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
x = 5
<?php endCode(); ?>
... since during a function call the value of the primitive will be copied instead of passing the reference of the primitive.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  static int x = 5;

  static void increase() {
    for(int i = 0; i < 10; i++) {
      x += 2;
    }
  }

  public static void main(String[] args) {
    increase(x);
    System.out.println("x = " + x);
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
class Example {
  static int x;

  static void increase() {
    for(int i = 0; i < 10; i++) {
      x += 2;
    }
  }

  public static void main(String[] args) {
    increase(x);
    System.out.println("x = " + x);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
x = 20
<?php endCode(); ?>
Member fields are initialized with default values even if not definied. (Numbers variables are initialized with zero, boolean variables are initialized with "false", objects are initialized with "null").
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    int x;
    for(int i = 0; i < 10; i++) {
      x += 2;
    }
    System.out.println("x = " + x);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
Can not be compiled, because local variables must always be initialized before reading it.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    int x = 3;
    int y = 0;
    double result = x / y;
    System.out.println("result = " + result);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
Exception in thread "main" java.lang.ArithmeticException: / by zero
        at Example.main(Example.java:5)
<?php endCode(); ?>
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    int x = 3;
    int y = 2;
    double result = x / y;
    System.out.println("result = " + result);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
result = 1.0
<?php endCode(); ?>
First the integer division is done, whose result is a whole number; then it is converted to double.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    int x = 3;
    int y = 2;
    double result = x / (double)y;
    System.out.println("result = " + result);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
result = 1.5
<?php endCode(); ?>
If any of the arguments is a floating point number, then both arguments will be converted to floating point numbers first, and then will the division be executed.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    int x = 3;
    int y = 0;
    double result = x / (double)y;
    System.out.println("result = " + result);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
result = Infinity
<?php endCode(); ?>
When dividing with floating point numbers, division by zero is enabled.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    int x = 0;
    int y = 0;
    double result = x / (double)y;
    System.out.println("result = " + result);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
result = NaN
<?php endCode(); ?>
When dividing with floating point numbers, division by zero is enabled.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    int x = 5;
    double result = x;
    System.out.println("result = " + result);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
result = 5.0
<?php endCode(); ?>
Integers can be converted to double with automatic conversion.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    double x = 5.3;
    int result = x;
    System.out.println("result = " + result);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
Can not be compiled. Double has a wider range then int, so it can not be casted implicitly.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    double x = 5.3;
    int result = (int)x;
    System.out.println("result = " + result);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
<?php startCode(); ?>
result = 5
<?php endCode(); ?>
Double has a wider range then int, so it can not be casted implicitly; 
however, explicit casting works, with the restriction, that the result will be a whole number.
<?php endExample(); ?>

<?php startExample(); ?>
What will be the result?
<?php startCode(); ?>
class Example {
  public static void main(String[] args) {
    double x = 0;
    while(x != 1.0) {
      x += 0.1;
    }
    System.out.println("x = " + x);
  }
}
<?php endCode(); ?>
<?php solution(); ?>
Will run in an infinite loop. Since floating point numbers have a finite precision, the value of <b>x</b> will never be exactly 1.0;
rather, something like 0.99999... We never use equality check on double or float types.
Either inequality should be checked:
<?php startCode(); ?>
while(x < 1.0) {
  x += 0.1;
}
<?php endCode(); ?>
... or we check, if its values is "near something":
<?php startCode(); ?>
while(true) {
  x += 0.1;
  if(Math.abs(x - 1.0) < 0.00001)
    break;
}
<?php endCode(); ?>
<?php endExample(); ?>

<?php ob_start(); ?></script><?php ob_end_clean(); ?>
      </div>

    <div><!-- main -->

  </body>
</html>
