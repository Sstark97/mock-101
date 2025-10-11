package printdate

import "time"

type Calendar interface {
	Today() time.Time
}
