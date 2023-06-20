create
user "${rootArtifactId}";
alter
user "${rootArtifactId}" with PASSWORD '${rootArtifactId}';
create schema "${rootArtifactId}";
alter
schema "${rootArtifactId}" owner to "${rootArtifactId}";
