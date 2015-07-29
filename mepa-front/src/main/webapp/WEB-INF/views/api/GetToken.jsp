<h5>URL: <a href="token/" target="_blank">/api/user/token/</a></h5>
<h5>Method: GET</h5>
<h5>Parameter: username, password</h5>
<h5>OutputXML:</h5>
<pre class="prettyprint">
&lt?xml version="1.0" encoding="UTF-8" standalone="yes"?>
&ltItems>
    &ltitems>
        &ltid>55ae606bf7d6d20afc68a2d1</id>
  &ltname>test1</name>
  &lt/items>
    &ltitems>
        &ltid>55ad5a82ade3561e340d53bc</id>
  &ltname>test2</name>
  &lt/items>
    &ltitems>
        &ltid>55af9a3aade3560a7c8a7695</id>
  &ltname>test3</name>
  &lt/items>
&lt/Items></pre>
<h5>For Output in Json: add .json at the end of URL</h5>
<h5>OutputJson:</h5>
<pre class="prettyprint">
{
  "items": [
    {
      "id": "55ae606bf7d6d20afc68a2d1",
      "name": "test1"
    },
    {
      "id": "55ad5a82ade3561e340d53bc",
      "name": "test2"
    },
    {
      "id": "55af9a3aade3560a7c8a7695",
      "name": "test3"
    }
  ]
}</pre>