# Picasso Utils

Code sample with some possibilities that Picasso can provide.
- Load Thumbnail before High Quality
  - Load a low quality imagem before the high quality and avoid the placeholder stay for a long time just like WhatsApp, Facebook, Instagram and other apps;
- Transformation
  - Transform images (crop, blur, etc) without using ```new Target()``` because Picasso have a weak reference with Target and, sometimes, the Garbage Collector collects the object before set the modification;


###### You can try the default example or put your own links and test.
![](https://media.giphy.com/media/xUPGcHc1B68yn8h8u4/giphy.gif) 
