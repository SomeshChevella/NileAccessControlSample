BEGIN;

-- The adam user is the administrator of the system.
-- With this power comes great responsibility (mostly cleaning up messes).
INSERT INTO "user" (username, password) VALUES ('adam', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');
INSERT INTO user_authorities (user_id, authorities) VALUES (1, 'ROLE_ADMINISTRATOR');

-- The courtney user is a regular customer. No special roles or authorities are granted.
-- A regular customer can place new orders and view their own past orders.
INSERT INTO "user" (username, password) VALUES ('courtney', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');

-- The lisa user is a customer with the additional ability to list new widgets on the platform.
INSERT INTO "user" (username, password) VALUES ('lisa', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');
INSERT INTO user_authorities (user_id, authorities) VALUES (3, 'LIST_WIDGET');

-- The emily user is an employee with the additional ability to view orders belonging to customers.
-- This is different than for a regular customer user who can only see his or her own orders.
INSERT INTO "user" (username, password) VALUES ('emily', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');
INSERT INTO user_authorities (user_id, authorities) VALUES (4, 'VIEW_CUSTOMER_ORDER');

-- The manny user is an employee and a manager. In addition to being able to view customer orders
-- this user can also cancel customer orders (in the case of e.g. a refund).
INSERT INTO "user" (username, password) VALUES ('manny', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');
INSERT INTO user_authorities (user_id, authorities) VALUES (5, 'VIEW_CUSTOMER_ORDER');
INSERT INTO user_authorities (user_id, authorities) VALUES (5, 'CANCEL_CUSTOMER_ORDER');

COMMIT;