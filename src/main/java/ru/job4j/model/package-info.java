@GenericGenerator(
     name = "ID_GENERATOR",
     strategy = "enhanced-sequence",
     parameters = {
          @Parameter(
               name = "sequence_name",
               value = "tasks_id_seq"
          ),
          @Parameter(
               name = "initial_value",
               value = "1000"
          ),
          @Parameter(
               name = "increment_size",
               value = "1"
          )
})
package ru.job4j.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;