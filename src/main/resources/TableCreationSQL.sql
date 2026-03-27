CREATE TYPE client_tier_enum AS ENUM('Standard', 'Premium', 'Private Banking');
CREATE TYPE client_net_worth_enum AS ENUM ('<$500K', '$500K-$2M', '$2M-$10M', '$10M+');

CREATE TABLE IF NOT EXISTS client (
	client_id INTEGER PRIMARY KEY,              /** auto increment is great for basic int, spring can generate UUID if desired */
	first_name TEXT NOT NULL,                   /** worth replacing with varchar later */
	last_name TEXT NOT NULL,
	email TEXT NOT NULL,
	phone TEXT NOT NULL, /** making text in case of country codes, dash format, etc */
	tier client_tier_enum NOT NULL,
	net_worth client_net_worth_enum NOT NULL
	/** Making everything not null right now because it feels right, might need to change later */
	/** Note to self, use ALTER TABLE later on to adjust */
);

CREATE TYPE advisory_service_type_enum AS ENUM ('Tax Planning', 'Estate Planning', 'Porfolio Review', 'Retirement Planning');
CREATE TYPE advisory_service_delivery_format_enum AS ENUM ('In-Person', 'Virtual', 'Hybrid');

CREATE TABLE IF NOT EXISTS advisory_service (
	advisory_service_id INTEGER PRIMARY KEY,
	service_name TEXT NOT NULL,
	service_type advisory_service_type_enum NOT NULL,
	delivery_format advisory_service_delivery_format_enum NOT NULL,
	is_active_status BOOLEAN DEFAULT TRUE, /** options are available / discontinued in write-up */
	annual_fee NUMERIC(10,2) /** copied from chinook-example's table creation for a price  */
);

CREATE TYPE engagement_status_enum AS ENUM ('Active', 'Paused', 'Completed');

CREATE TABLE IF NOT EXISTS engagement (
	engagement_id INTEGER PRIMARY KEY,
	advisory_service_id INTEGER REFERENCES advisory_service(advisory_service_id),
	client_id INTEGER REFERENCES client(client_id),
	start_date DATE DEFAULT CURRENT_DATE,
	engagement_status engagement_status_enum NOT NULL,
	notes TEXT
);
