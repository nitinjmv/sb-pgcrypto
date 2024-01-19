package com.example.sbpgcrypto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCT_TABLE")
public class Account {

    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQUENCE")
    @SequenceGenerator(name = "ACCOUNT_SEQUENCE", sequenceName = "ACCOUNT_SEQUENCE", allocationSize = 1)
    private Long id;

//    @ColumnTransformer(
//            read =  "pgp_sym_decrypt(" +
//                    "    pan, " +
//                    "    current_setting('encrypt.key')" +
//                    ")",
//            write = "pgp_sym_encrypt( " +
//                    "    ?, " +
//                    "    current_setting('encrypt.key')" +
//                    ") "
//    )

    @ColumnTransformer(
            forColumn = "ACCOUNT_NUMBER",
            read = "pgp_sym_decrypt(ACCOUNT_NUMBER::bytea, '${pgcrypto.secret}')",
            write = "pgp_sym_encrypt(?, '${pgcrypto.secret}')"
    )
    @Column(name = "ACCOUNT_NUMBER", updatable = false)
    private String number;

    @Column(name = "ACCOUNT_TYPE")
    private String type;

    @Column(name = "CREATED_DATE", updatable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private Instant createdDate;

    @Column(name = "STS")
    private String status;

}
