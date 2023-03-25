<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Restaurants</h2>
                <xsl:for-each select="//restaurants">

                <table border="1">
                    <tr bgcolor="#dadada">
                        <th>name</th>
                        <th>location</th>
                        <th>phone</th>
                        <th>email</th>
                        <th>rate</th>
                        
                        
                    </tr>
                    <xsl:for-each select="//restaurants">
                        <xsl:sort select="name" />
                        <tr>
                            <td>
                                <xsl:value-of select="name" />
                            </td>
                            <td>
                                <xsl:value-of select="location" />
                            </td>
                            <td>
                                <xsl:value-of select="phone" />
                            </td>
                            <td>
                                <xsl:value-of select="email" />
                            </td>
                            <td>
                                <xsl:value-of select="rate" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
                </xsl:for-each>
            </body>
        </html>  
        
        <style>
        table {
            border-collapse: collapse;
            width: 100%;
            font-family: Arial, sans-serif;
            font-size: 14px;
            }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
            }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
            }

        tr:nth-child(even) {
            background-color: #f2f2f2;
            }
        </style>

    </xsl:template>

</xsl:stylesheet>