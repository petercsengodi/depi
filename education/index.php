<?php

  header("Cache-Control: no-cache, must-revalidate"); // HTTP/1.1
  header("Expires: Sat, 26 Jul 1997 05:00:00 GMT"); // Date in the past
  header("Content-type: text/html; charset=utf-8");
  header("Pragma: no-cache");

?><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Home Made Education Material</title>
    <meta name="author" content="csega">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="description" content="Amateur education site for programming languages, like Java 8.">
    <meta name="keywords" content="programing languages, java, education">

    <style>
      body {
        background: #FCFCFC;
      }

      div.main {
        float: left;
      }

      div.tile {
        float: left;
        margin: 5px;
        padding: 10px;
        width: 150px;
        height: 200px;
        text-align: center;
        border: 2px solid #000000;
        background: #F0F0F0;
        font-family: Arial;
        font-size: 32px;
        font-weight: bold;
        cursor: pointer;
      } 

      hr {
        margin-top: 5px;
        margin-bottom: 5px;"
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
    </style>

  </head>
  <body>

    <div class="breadcrumbs">
      Main
    </div>

    <h1>Home Made Education Material</h1>

    <div class="main">

      <div class="tile" onclick="window.location = './java8basics/index.php'">JAVA 8 basics<div>

    <div><!-- main -->

    <div style="float: none; clear: both;"></div>
    <div style="margin-bottom: 600px;">&nbsp;</div>


  </body>
</html>
