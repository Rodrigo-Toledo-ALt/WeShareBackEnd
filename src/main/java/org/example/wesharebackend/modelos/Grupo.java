package org.example.wesharebackend.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "grupo", schema = "weshare", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Grupo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_grupo", nullable = false)
    private LocalDate fechaCreacion;

    @OneToMany(targetEntity = Pago.class, mappedBy = "grupo")
    private Set<Pago> pagos;



}
