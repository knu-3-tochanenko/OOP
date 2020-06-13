SELECT id, radius
FROM (
         SELECT id,
                ((a_length * b_length * c_length) /
                 (4 * sqrt(abs(p * (p - a_length) * (p - b_length) * (p - c_length))))) as radius
         FROM (
                  SELECT id, (a_length + b_length + c_length) / 2 as p, a_length, b_length, c_length
                  FROM (
                           SELECT t.id,
                                  SQRT(a.x * a.x + a.y * a.y) as a_length,
                                  SQRT(b.x * b.x + b.y * b.y) as b_length,
                                  SQRT(c.x * c.x + c.y * c.y) as c_length
                           FROM triangles t
                                    INNER JOIN dots a ON t.a = a.id
                                    INNER JOIN dots b ON t.b = b.id
                                    INNER JOIN dots c ON t.c = c.id
                           GROUP BY t.id, a.id, b.id, c.id) as tabc2
              ) as tabc) as ir
WHERE ir.radius <= ?;