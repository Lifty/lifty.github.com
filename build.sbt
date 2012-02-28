import com.jsuereth.sbtsite.SiteKeys

seq(site.settings: _*)

seq(ghpages.settings: _*)

git.remoteRepo := "git@github.com/Lifty/lifty.github.com.git"

// The most interesting part. I'm adding pamflet output 
// folder to the site mappings. Now ghpages plugin knows 
// that files generated by pamflet plugin should also be 
// published. Note, that PamfletKeys.write is actually 
// hack in order to generate pamflet before site
// would be published.
SiteKeys.siteMappings <<=
  (SiteKeys.siteMappings, PamfletKeys.write, PamfletKeys.output) map { (mappings, _, dir) =>
	mappings ++ (dir ** "*.*" x relativeTo(dir))
}

// set the branch used by gh-pages
(com.jsuereth.git.GitKeys.gitBranch) := Some("master")