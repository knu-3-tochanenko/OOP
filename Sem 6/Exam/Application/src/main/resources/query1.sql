SELECT t.id, ABS(? - (ABS((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y)) / 2)) AS area
FROM triangles t
         INNER JOIN dots a ON t.a = a.id
         INNER JOIN dots b ON t.b = b.id
         INNER JOIN dots c ON t.c = c.id
GROUP BY t.id, a.id, b.id, c.id
ORDER BY area ASC
LIMIT 1;